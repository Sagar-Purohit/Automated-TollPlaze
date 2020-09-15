package com.example.dell.tollapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class Tooll extends AppCompatActivity {
    ProgressDialog progressDialog;
    ArrayList<Tooll_pojo> cm=new ArrayList<>();
    String rid = "";
ArrayList<Integer> two = new ArrayList<>();
    ArrayList<Integer> tw = new ArrayList<>();
    ArrayList<Integer> fw = new ArrayList<>();
    int twoors = 0;
    int twors = 0;
    int fwrs = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tooll);
        Intent update=getIntent();
        Rootview commonclass;
        commonclass=(Rootview) update.getSerializableExtra("array");
        rid = commonclass.getRid();
        MyAsyncTask myAsyncTask= new MyAsyncTask();
        myAsyncTask.execute();

    }
    public void onbook(View v)
    {
        Intent i = new Intent(Tooll.this,BookingActivity.class);
        i.putExtra("twors",twoors);
        i.putExtra("twoors",twors);
        i.putExtra("fwrs",fwrs);
        startActivity(i);
    }
    public class MyAsyncTask extends AsyncTask<String,Void,String>
    {
        JSONObject object = new JSONObject();
        HttpURLConnection httpurlConnection;

        @Override
        protected void onPreExecute()
        {
            progressDialog = new ProgressDialog(Tooll.this);
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();
            super.onPreExecute();
            try {
                object.put("rid",rid);
            } catch (JSONException e) {


            }

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
                com.getURL("toll_display.php");
                httpurlConnection = com.getconnect();


                DataOutputStream outputStream = new DataOutputStream(httpurlConnection.getOutputStream());
                outputStream.write(object.toString().getBytes());



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

                        Tooll_pojo commonclass=new Tooll_pojo();
                        //  commonclass.setId(Integer.parseInt(jsonObject2.getString("pid").toString()));
                        //pidArray.add(jsonObject2.getString("pid").toString());
                        commonclass.setFw(jsonObject2.getString("fw").toString());
                        commonclass.setTid(jsonObject2.getString("tid").toString());
                        commonclass.setToollname(jsonObject2.getString("tollname").toString());
                        commonclass.setRid(jsonObject2.getString("rid").toString());
                        commonclass.setTow(jsonObject2.getString("tow").toString());
                        commonclass.setTw(jsonObject2.getString("tw").toString());
                        cm.add(commonclass);
                        two.add(Integer.parseInt(jsonObject2.getString("tow").toString()));
                        fw.add(Integer.parseInt(jsonObject2.getString("fw").toString()));
                        tw.add(Integer.parseInt(jsonObject2.getString("tw").toString()));
                    }

                    for (int i=0;i < two.size();i++)
                    {
                        twors = twors + two.get(i) ;
                    }
for (int j = 0 ; j< tw.size();  j ++)
{
    twoors = twoors + tw.get(j);

}
                    for (int z = 0 ; z< fw.size();   z++)
                    {
                        fwrs = fwrs + fw.get(z);

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
            Customtooladapter prodAdapter = new Customtooladapter(getApplicationContext(),cm);
            // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,pnameArray);
            listView.setAdapter(prodAdapter);
            progressDialog.dismiss();
        }
    }
}


