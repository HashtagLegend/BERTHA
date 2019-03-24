package com.example.bertha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bertha.Model.CombinedSendData;
import com.example.bertha.REST.PostHttpTask;
import com.example.bertha.REST.ReadHttpTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class SendDataToDb extends AppCompatActivity {

    public static final String MINE = "MINE";
    public static final String urlWristbandData = "https://berthawristbandrestprovider.azurewebsites.net/api/wristbanddata/";
    public static final String postToDbUrl = "https://berthabackendrestprovider.azurewebsites.net/api/data/";

    private TextView sendDataTV;

    private int deviceId;
    private double pm25;
    private double pm10;
    private int co2;
    private int o3;
    private double pressure;
    private double temperature;
    private int humidity;
    private double latitude;
    private double longitude;
    private long utc;
    private int noise;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data_to_db);

        sendDataTV = findViewById(R.id.sendDataActTV);
    }




    public void getData(View view) {
        generateInputData task = new generateInputData();
        task.execute(urlWristbandData);
    }

    public void sendData(View view) {
        sendData();
    }

    public void goBackButton(View view) {
        finish();
    }

    private class generateInputData extends ReadHttpTask {
        @Override
        protected void onPostExecute(CharSequence jsonString) {
            Log.d(MINE, "onPostExecute: called");

            try {
                JSONObject jsonObject = new JSONObject(jsonString.toString());

                deviceId = jsonObject.getInt("deviceId");
                pm25 = jsonObject.getDouble("pm25");
                pm10 = jsonObject.getDouble("pm10");
                co2 = jsonObject.getInt("co2");
                o3 = jsonObject.getInt("o3");
                pressure = jsonObject.getDouble("pressure");
                temperature = jsonObject.getDouble("temperature");
                humidity = jsonObject.getInt("humidity");
                latitude = 55.44;
                longitude = 34.32;
                utc = new Date().getTime();
                noise = 5;
                userId = "patr2";

                Log.d(MINE, "generateInputData " + jsonObject.toString());
            } catch (JSONException ex) {
                Log.d(MINE, "generateInputData" + ex.getMessage());
            }
        }
    }

    public void sendData(){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("deviceId", deviceId);
            jsonObject.put("pm25", pm25);
            jsonObject.put("pm10", pm10);
            jsonObject.put("co2", co2);
            jsonObject.put("o3", o3);
            jsonObject.put("pressure", pressure);
            jsonObject.put("temperature", temperature);
            jsonObject.put("humidity", humidity);
            jsonObject.put("latitude", latitude);
            jsonObject.put("longitude", longitude);
            jsonObject.put("utc", utc);
            jsonObject.put("noise", noise);
            jsonObject.put("userId", userId);
            String jsonDocument = jsonObject.toString();
            sendDataTV.setText(jsonDocument);
            PostHttpTask task = new PostHttpTask();
            task.execute(postToDbUrl, jsonDocument);
        } catch (JSONException ex) {
            sendDataTV.setText(ex.getMessage());
        }
    }


}
