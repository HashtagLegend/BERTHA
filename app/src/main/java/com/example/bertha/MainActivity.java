package com.example.bertha;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bertha.Model.CombinedSendData;
import com.example.bertha.REST.PostHttpTask;
import com.example.bertha.REST.ReadHttpTask;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String MINE = "MINE";
    public static final String urlWristbandData = "https://berthawristbandrestprovider.azurewebsites.net/api/wristbanddata/";
    public static final String postToDbUrl = "https://berthabackendrestprovider.azurewebsites.net/api/data/";
    Button getDataButton;
    private TextView mainMessageTv;

    private CombinedSendData combinedDataNew, combinedDataNewTestPurpose;

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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataButton = findViewById(R.id.btnMainGetData);
        mainMessageTv = findViewById(R.id.mainMessageTv);

        //Testdata: used as long as tablet wont recieve data due to SSL error:
        //TODO Uncomment this as well
        // getLatLongMethod();
        combinedDataNewTestPurpose = new CombinedSendData(1616384779, 10, 10, 10, 25, 10, 10, 10, new Date().getTime(), latitude, longitude, 2, "patr");

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

    public void SendDataToDBButtonPressed(View view) {
        //ReadFromWristbandTask getData = new ReadFromWristbandTask();
        //getData.execute(urlWristbandData);
        Log.d(MINE, "SendDataToDBButtonPressed: now we wait 5 secs");
        //TODO optimize this handler
        // (new Handler()).postDelayed(this::postDataToDb, 5000);
        postDataToDb();
        Log.d(MINE, "SendDataToDB: pressed");
        Log.d(MINE, "latlong data: " + latitude + longitude);
    }

    private class ReadFromWristbandTask extends ReadHttpTask {
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

                getLatLongMethod();

            } catch (JSONException ex) {
                mainMessageTv.setText(ex.getMessage());
                Log.d(MINE, "ReadFromWristBandTask" + ex.getMessage());
            }
        }
    }

    public void postDataToDb() {
        String userId = "patr";
        int noise = 2;

        //combinedDataNew = new CombinedSendData(deviceId, co2, o3, humidity, pm25, pm10, pressure, temperature, new Date().getTime(), latitude, longitude, noise, userId);

        //Converts object to json with gson
        Gson gson = new Gson();
        Log.d(MINE, "postDataToDb: " + combinedDataNewTestPurpose.toString());
        String jsonDoc = gson.toJson(combinedDataNewTestPurpose);
        PostHttpTask task = new PostHttpTask();
        task.execute(postToDbUrl, jsonDoc);
        Log.d(MINE, "postDataToDb: " + jsonDoc);
    }


    public void getLatLongMethod() {
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        showLocation(location);
        //TODO uncomment theese
        //longitude = location.getLongitude();
        //latitude = location.getLatitude();

        Log.d(MINE, "longitude:" + longitude +" latitude: " + latitude);
    }

    private void showLocation(Location location){
        if(location == null){
            Toast.makeText(this, "Location is null", Toast.LENGTH_LONG).show();
            return;
        }

    }

    public void doEvery10Seconds(){
        while(true){
            (new Handler()).postDelayed(this::write1, 5000);
        }

    }

    public void write1(){
        Log.d(MINE, "write: waited 5 seconds");
    }



}
