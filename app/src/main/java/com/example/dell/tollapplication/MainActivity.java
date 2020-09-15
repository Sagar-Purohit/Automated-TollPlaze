package com.example.dell.tollapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {
    EditText edUname,edPwd;
    String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences=getSharedPreferences("Myfile",MODE_PRIVATE);
        Boolean status=sharedPreferences.getBoolean("islogin",false);
        String role=sharedPreferences.getString("role",null);
        if(status==true)
        {
            if(role.equalsIgnoreCase("user")) {
                //     Intent intent = new Intent(this, USerHome.class);
                // startActivity(intent);
            }
            else  if(role.equalsIgnoreCase("gym"))
            {
                //   Intent intent = new Intent(this, GymHome.class);
                // startActivity(intent);
            }
        }
        init();
    }
    public void init()
    {
        edUname = (EditText) findViewById(R.id.edUname);
        edPwd = (EditText) findViewById(R.id.edPwd);
    }
    boolean validation()
    {
        boolean flag = true;
        if(CheckValidations.checkEmail(getApplicationContext(),edUname.getText().toString()))
        {
            flag = false;
            edUname.setError("plz enter valid email address");
        }
        if(CheckValidations.checkIsEmpty(getApplicationContext(),edPwd.getText().toString()))
        {
            flag = false;
            edPwd.setError("plz enter password");
        }
        return flag;
    }

    public void ForgetPass(View view){
        Intent intent=new Intent(this,ForgetPassword.class);
        startActivity(intent);
    }
    public  void  onlogin(View view) {

        username = edUname.getText().toString();
        password = edPwd.getText().toString();
        if (validation()) {
            Asynchttpclass asynchttpclass = new Asynchttpclass();
            asynchttpclass.execute();
        }
    }
    class  Asynchttpclass extends AsyncTask<Void,Void,JSONObject>
    {

        JSONObject object;
        HttpURLConnection httpurlConnection;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            object=new JSONObject();
            try {
                object.put("username",username);
                object.put("password",password);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        @Override
        protected JSONObject doInBackground(Void... params) {


            try {

                Common_Class com=new Common_Class();
                com.getURL("login.php");
                httpurlConnection=com.getconnect();


                DataOutputStream outputStream=new DataOutputStream(httpurlConnection.getOutputStream());
                outputStream.write(object.toString().getBytes());




                int code=httpurlConnection.getResponseCode();

                if(code==200)
                {

                    String str=null;
                    StringBuilder builder=new StringBuilder();

                    BufferedReader reader=new BufferedReader(new InputStreamReader(httpurlConnection.getInputStream()));

                    while ((str=reader.readLine())!=null)
                    {
                        builder.append(str);
                    }
                    object=new JSONObject(builder.toString());

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return object;
        }

        @Override
        protected void onPostExecute(JSONObject s) {
            super.onPostExecute(s);
            String msg = null;
            String role= null;
            String status = null;

            try {
                msg=  s.getString("lid");
                role =  s.getString("role");
                status = s.getString("msg");
                if (status.equalsIgnoreCase("Success"))
                {
                    SharedPreferences sharedPreferences=getSharedPreferences("Myfile",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putBoolean("islogin",true);
                    editor.putString("lid",msg);
                    editor.putString("role",role);
                    editor.putString("You have logged in successfully",null);
                    editor.commit();
                    if(role.equalsIgnoreCase("user")) {
                         Intent intent = new Intent(getApplicationContext(),Viewroot.class );
                         startActivity(intent);
                    }
                    else  if(role.equalsIgnoreCase("admin"))
                    {
                        //Intent intent = new Intent(getApplicationContext(), );
                        //startActivity(intent);
                    }

                }
                else {
                    Toast.makeText(getApplicationContext(),"UserName or Password Wrong.Please Try again",Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(),""+msg,Toast.LENGTH_LONG).show();

            Toast.makeText(getApplicationContext(),""+role,Toast.LENGTH_LONG).show();

        }
    }



    public void onreg(View view)
    {
        Intent intent=new Intent(this,OptionsForRegistration.class);
        //intent.putExtra("data",obj);
        startActivity(intent);
    }

}
