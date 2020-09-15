package com.example.dell.tollapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Viewroot extends AppCompatActivity implements Deleget {
    ProgressDialog progressDialog;
    ArrayList<Rootview> cm=new ArrayList<>();
    String lid = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewroot);
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }

    @Override
    public void onclickemethod(Rootview rootview) {
        Toast.makeText(getApplicationContext(),"tooo",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Viewroot.this,Tooll.class);
        intent.putExtra("array",rootview);
        startActivity(intent);
    }
/*

    @Override
    public void onitemclick(commonclass c) {
        if (c != null) {
            //  int pos=Integer.parseInt(c.getId());
            Intent update=new Intent(getApplication(),single_arraylist_update.class);
            update.putExtra("Array",c);
            startActivity(update);
          */
/*  StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                // URL url = new URL("http://192.168.0.124:8080/PhpProject1/insert.php");
                commonurl commonurl = new commonurl();
                String u = commonurl.geturl("addtocartproduct.php");
                URL url = new URL(u);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setRequestProperty("Accept", "application/json");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection.connect();


                JSONObject jsonObject = new JSONObject();
                jsonObject.put("pid",c.getId());
                //   jsonObject.put("Pr_id",productData.getPid());
               jsonObject.put("pname",c.getName());
                jsonObject.put("price",c.getPrice());

                jsonObject.put("lid",MyShred.getLoginId(Viewsinglearraylist.this));

                DataOutputStream outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                outputStream.write(jsonObject.toString().getBytes());

                int code = httpURLConnection.getResponseCode();
                if (code == 200) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String s;
                    while ((s = bufferedReader.readLine()) != null) {
                        stringBuilder.append(s);

                    }
                    JSONObject object = new JSONObject(stringBuilder.toString());
                    // String msg=object.getString("response");
                    String lid = object.getString("lid");
                    // Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();


                    if (!lid.equals("-1")) {


                    }
                    else {

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

*//*

            //    Intent i1=new Intent(Viewsinglearraylist.this,single_arraylist_update.class);

            //   startActivity(i1);

           */
/*
                    delete
           StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try
            {
                //URL url = new URL("http://192.168.0.104:8080/PhpProject1/book.php");
                commonurl commonurl=new commonurl();
                String u=commonurl.geturl("productsdelete.php");
                URL url=new URL(u);

                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type","application/json");
                httpURLConnection.setRequestProperty("Accept","application/json");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.connect();

                JSONObject jsonObject=new JSONObject();
                jsonObject.put("pid",c.getId());
              //  jsonObject.put("name",name.getText().toString());

                DataOutputStream outputStream=new DataOutputStream(httpURLConnection.getOutputStream());
                outputStream.write(jsonObject.toString().getBytes());

                int code=httpURLConnection.getResponseCode();
                if(code==200)
                {
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    StringBuilder stringBuilder=new StringBuilder();
                    String s;1
                    while ((s=bufferedReader.readLine()) !=null)
                    {
                        stringBuilder.append(s);

                    }
                    JSONObject object=new JSONObject(stringBuilder.toString());
                    String msg=object.getString("response");

                    Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();

                }


            }
            catch (Exception e)
            {

            }
            Intent i1=new Intent(Viewsinglearraylist.this,Viewsinglearraylist.class);
            startActivity(i1);

        }*//*


        }
    }
*/


    public class MyAsyncTask extends AsyncTask<String,Void,String>
    {
        JSONObject object;
        HttpURLConnection httpurlConnection;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Viewroot.this);
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();
        /*    SharedPreferences sharedPreferences=getSharedPreferences("MyPref",MODE_PRIVATE);

            if(sharedPreferences != null) {
                lid = sharedPreferences.getString("id", "-1");
            }
*/
        }

        @Override
        protected String doInBackground(String... params)
        {
            try {
                Common_Class com = new Common_Class();
                com.getURL("Routewebservce.php");
                httpurlConnection = com.getconnect();


             /*   DataOutputStream outputStream = new DataOutputStream(httpurlConnection.getOutputStream());
                outputStream.write(object.toString().getBytes());

*/

                int res = httpurlConnection.getResponseCode();
                if (res == 200) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpurlConnection.getInputStream()));

                    String line = "";
                    StringBuilder response = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                        response.append(line);
                    }
                    JSONObject jsonObject1 = new JSONObject(response.toString());
                    JSONArray prodsJsonArr = jsonObject1.getJSONArray("response");
                    //Toast.makeText(this, lid, Toast.LENGTH_LONG).show();
                    for(int i=0; i< prodsJsonArr.length();i++)
                    {
                        JSONObject jsonObject2 = prodsJsonArr.getJSONObject(i);

                        Rootview commonclass=new Rootview();
                        //  commonclass.setId(Integer.parseInt(jsonObject2.getString("pid").toString()));
                        //pidArray.add(jsonObject2.getString("pid").toString());
                     commonclass.setDestination(jsonObject2.getString("desti").toString());
                        commonclass.setName(jsonObject2.getString("name").toString());
                        commonclass.setSource(jsonObject2.getString("sorce").toString());
                     commonclass.setRid(jsonObject2.getString("rid").toString());
                        cm.add(commonclass);
                    }

                }
            } catch (Exception ex) {
                ex.printStackTrace();
                //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String pname)
        {
            ListView listView = (ListView )findViewById(R.id.lvview);
            Customadapterroot prodAdapter = new Customadapterroot(getApplicationContext(),cm,Viewroot.this);
            // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,pnameArray);
            listView.setAdapter(prodAdapter);
            progressDialog.dismiss();
        }
    }
}

