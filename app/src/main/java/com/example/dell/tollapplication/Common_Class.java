package com.example.dell.tollapplication;

import java.io.IOException;
import java.io.Serializable;

import android.app.ProgressDialog;
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
import java.net.URL;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Dell on 10-10-2017.
 */

public class Common_Class {
    URL url=null;

    public URL  getURL(String url1) {


        try {

            url = new URL("http://172.20.10.6:8080/Tollapp/"+url1);

        } catch (Exception e) {

        }
        return url;
    }

    public HttpURLConnection getconnect(){

        HttpURLConnection urlConnection=null;

        //Connect
        try {
            urlConnection= (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            //Set Content-type
            urlConnection.setRequestProperty("Content-Type","application/json");
            //set response type
            urlConnection.setRequestProperty("Accept","application/json");

            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return urlConnection;
    }
}
