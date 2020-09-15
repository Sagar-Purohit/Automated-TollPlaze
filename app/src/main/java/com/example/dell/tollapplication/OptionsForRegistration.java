package com.example.dell.tollapplication;

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

public class OptionsForRegistration extends AppCompatActivity {
    EditText name, password,email,cno,age;
    String name1, password1,email1,cno1,age1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_for_registration);
       name = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.name);
        cno = (EditText) findViewById(R.id.cno);

age = (EditText) findViewById(R.id.age);
    }
    public void onlogin(View view)
    {
        if(validation()) {
            Asynchttpclass asynchttpclass = new Asynchttpclass();
            asynchttpclass.execute();
        }
    }

boolean validation()
{
    boolean flag = true;
    if(CheckValidations.checkEmail(getApplicationContext(),name.getText().toString()))
    {
        flag = false;
        name.setError("plz enter email");
    }
    if (CheckValidations.checkIsEmpty(getApplicationContext(),email.getText().toString()))
    {
        flag = false;
        email.setError("plz enter name");
    }

    if(CheckValidations.checkIsEmpty(getApplicationContext(),password.getText().toString()))
    {
        flag = false;
        password.setError("plz enter password");
    }
    if(CheckValidations.checkPhone(getApplicationContext(),cno.getText().toString()))
    {
        flag = false;
        cno.setError("plz ennetr valid digit");
    }
    if(CheckValidations.checkIsEmpty(getApplicationContext(),age.getText().toString()))
    {
        flag = false;
        age.setError("plz enter age");
    }
    return  flag;
}


    class Asynchttpclass extends AsyncTask<Void, Void, JSONObject> {

        JSONObject object;
        HttpURLConnection httpurlConnection;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            name1 = name.getText().toString();
            password1 = password.getText().toString();
            email1  = email.getText().toString();
            cno1 = cno.getText().toString();
            age1 = age.getText().toString();
            object = new JSONObject();
            try {
                object.put("uname", name1);
                object.put("pwd", password1);
                object.put("name",email1);
                object.put("cno",cno1);
                object.put("age",age1);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected JSONObject doInBackground(Void... params) {


            try {

                Common_Class com = new Common_Class();
                com.getURL("registration.php");
                httpurlConnection = com.getconnect();


                DataOutputStream outputStream = new DataOutputStream(httpurlConnection.getOutputStream());
                outputStream.write(object.toString().getBytes());


                int code = httpurlConnection.getResponseCode();

                if (code == 200) {

                    String str = null;
                    StringBuilder builder = new StringBuilder();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(httpurlConnection.getInputStream()));

                    while ((str = reader.readLine()) != null) {
                        builder.append(str);
                    }
                    object = new JSONObject(builder.toString());

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
            String role = null;
            String status = null;

            try {
                msg = s.getString("response");
              /*  role =  s.getString("role");
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
                        //  Intent intent = new Intent(getApplicationContext(), );
                        // startActivity(intent);
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
*/
                //}
                // }

            }
            catch (Exception e)
            {

            }
            Toast.makeText(getApplicationContext(),""+msg,Toast.LENGTH_LONG).show();

        }
    }
}