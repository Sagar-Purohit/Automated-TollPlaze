package com.example.dell.tollapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Changepassword extends AppCompatActivity implements View.OnClickListener {

    EditText et_new_pwd,et_cnfrm_pwd;
    Button btn_reset_pwd;
    ProgressDialog progressDialog;
    String s_pwd,k,s_email;
    URL url;
    HttpURLConnection httpURLConnection;
    RelativeLayout rs_r;
    boolean checkvalidatio=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        et_new_pwd=(EditText)findViewById(R.id.et_new_pwd);
        et_cnfrm_pwd=(EditText)findViewById(R.id.et_cnfrm_pwd);
        btn_reset_pwd=(Button)findViewById(R.id.btn_reset_pwd);


        SharedPreferences shared=getSharedPreferences("MyPref",MODE_PRIVATE);
        s_email=shared.getString("s_email",null);

        btn_reset_pwd.setOnClickListener(this);

    }

    private void Validation()
    {
        if(et_new_pwd.getText().toString().isEmpty()||et_new_pwd.getText().toString().length()!=8)
        {
            et_new_pwd.setError("Please Enter valid Password");
            checkvalidatio=false;
        }

        else if(et_cnfrm_pwd.getText().toString().isEmpty())
        {
            et_cnfrm_pwd.setError("Please confirm Password");
            checkvalidatio=false;
        }

        else if(!et_cnfrm_pwd.getText().toString().equals(et_new_pwd.getText().toString()))
        {
            et_cnfrm_pwd.setError("Password does not match!");
            checkvalidatio=false;

        }
        else
        {
            checkvalidatio=true;
        }
    }

    @Override
    public void onClick(View v) {


        if (v==btn_reset_pwd)
        {
            //AsyncHttptask asynchttptask = new AsyncHttptask();
            //asynchttptask.execute();
            Validation();
            if(checkvalidatio) {
                AsyncHttpTask asyncHttpTask = new AsyncHttpTask();
                asyncHttpTask.execute();
            }

        }
    }

    private class AsyncHttpTask extends AsyncTask<String,Void,String>
    {

        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(Changepassword.this);
            progressDialog.setMessage("Loading....");
            progressDialog.show();

            s_pwd=et_new_pwd.getText().toString();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                url=new URL("http://172.20.10.6:8080/Tollapp/update_password.php");
                httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestProperty("Content-Type","application/json");
                httpURLConnection.setRequestProperty("Accept","application/json");
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.connect();

                DataOutputStream out=new DataOutputStream(httpURLConnection.getOutputStream());
                JSONObject jsoninput=new JSONObject();

                jsoninput.put("pwd",s_pwd);
                jsoninput.put("un",s_email);

                out.write(jsoninput.toString().getBytes());

                int responseCode=httpURLConnection.getResponseCode();
                if(responseCode==httpURLConnection.HTTP_OK)
                {
                    StringBuffer sb=new StringBuffer();
                    BufferedReader br=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String m="";

                    while ((m=br.readLine())!=null)
                    {
                        sb.append(m);
                        JSONObject j=new JSONObject(sb.toString());
                        k=j.getString("res");
                    }
                }
            }

            catch (Exception e) {
                e.printStackTrace();
            }
            return k;

        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            Toast.makeText(getApplication(),k,Toast.LENGTH_LONG).show();



            Intent intent=new Intent(Changepassword.this,MainActivity.class);
            startActivity(intent);
        }
    }
}
