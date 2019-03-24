package com.example.bertha.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bertha.Model.CombinedSendData;
import com.example.bertha.R;

public class DataActivity extends AppCompatActivity {

    public static final String DATA = "DATA";

    private CombinedSendData data;
    private TextView header, latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Intent intent = getIntent();
        data = (CombinedSendData) intent.getSerializableExtra(DATA);

        header = findViewById(R.id.DataHeaderTV);
        header.setText("DTG(Data tids gruppe): " + data.getUtc());

        latitude = findViewById(R.id.dataLatitude);
        latitude.setText("Latitude: " + data.getLatitude());

        longitude = findViewById(R.id.dataLongitude);
        longitude.setText("Longitude: " + data.getLongitude());
    }

    public void goBack(View view) {finish();}
}
