package com.example.bertha;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bertha.Model.Data;
import com.example.bertha.REST.PostHttpTask;
import com.example.bertha.REST.ReadHttpTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String MINE = "MINE";
    public static final String urlWristbandData = "https://berthawristbandrestprovider.azurewebsites.net/api/wristbanddata/";
    public static final String URLBooks = "http://anbo-restserviceproviderbooks.azurewebsites.net/Service1.svc/books";
    private Button getDataButton;
    private TextView mainMessageTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataButton = findViewById(R.id.btnMainGetData);

        mainMessageTv = findViewById(R.id.mainMessageTv);




    }

    public void goToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);


    }

    @Override
    protected void onResume() {
        super.onResume();
        ReadFromWristbandTask readTask = new ReadFromWristbandTask();
        readTask.execute(urlWristbandData);
    }

    private class ReadFromWristbandTask extends ReadHttpTask {
        @Override
        protected void onPostExecute(CharSequence jsonString) {
            Log.d(MINE, "onPostExecute: called");

            //---------------GSON Builder, not able to split string
            /*
            Gson gson = new GsonBuilder().create();

            final Data data = gson.fromJson(jsonString.toString(), Data.class);
            */

            try{
                JSONObject jsonObject = new JSONObject(jsonString.toString());

                int deviceId = jsonObject.getInt("deviceId");
                double pm25 = jsonObject.getDouble("pm25");
                double pm10 = jsonObject.getDouble("pm10");
                int co2 = jsonObject.getInt("co2");
                int o3 = jsonObject.getInt("o3");
                double pressure = jsonObject.getDouble("pressure");
                double temperature = jsonObject.getDouble("temperature");
                int humidity = jsonObject.getInt("humidity");

                final Data data = new Data(deviceId, co2, o3, humidity, pm25, pm10, pressure, temperature);

                Log.d(MINE, "onPostExecute: " + data.toString());
                TextView text = findViewById(R.id.mainDataTv);
                text.setText(data.toString());

            } catch (JSONException ex){
                mainMessageTv.setText(ex.getMessage());
                Log.d(MINE, "onPostExecute: " + ex.getMessage());
            }
        }
    }

    public void postDataToDb(){
        try{
            JSONObject jsonObject = new JSONObject();
            //ToDo create json object



            Log.d(MINE, "onPostExecute: ");


        } catch (JSONException ex){
            mainMessageTv.setText(ex.getMessage());
            Log.d(MINE, "postDataToDb: " + ex.getMessage());
        }
    }



}
