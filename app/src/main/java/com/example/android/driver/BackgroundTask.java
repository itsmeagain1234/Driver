package com.example.android.driver;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.MalformedInputException;
import java.util.Vector;

/**
 * Created by android on 6/2/16.
 */
public class BackgroundTask extends AsyncTask<String, Void, String>{
    private Context context;
    static Vector vector=new Vector();
   // static String status;
    BackgroundTask(Context ctx) {
        this.context = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String loginURL = "http://192.168.2.9/android/driverLogin.php";
        String registerURL = "http://192.168.2.10/android/DriverRegister.php";
        String method = params[0];
        //if(method.equals("login")) {
            String username = params[1];
            String password = params[2];

            try {
                URL url = new URL(loginURL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                OutputStream outputStream = urlConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("driverName", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";

                while((line = bufferedReader.readLine()) != null) {
                    response += line;
                    //Log.d("test", line);
                }

                bufferedReader.close();
                inputStream.close();
                urlConnection.disconnect();
                //Log.d("test", response);
                return response;



            } catch (MalformedURLException e) {
                //
               Log.d("test", e.toString());


             } catch (IOException e) {
                Log.d("test", e.toString());
            }
        //}
        return null;

    }

    @Override
    protected void onPostExecute(String s) {
       // status=s;

        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
        for(String name:s.split(" "))
        {
            vector.add(name);
           // Log.d("back", name);



    }
        Log.d("back",vector.get(1).toString());
        Intent intent=new Intent(context,AfterLogin.class);
//        s

}}
