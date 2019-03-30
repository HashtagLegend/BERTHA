package com.example.bertha.REST;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;

import static android.content.Context.LOCATION_SERVICE;


public class ReadHttpTask extends AsyncTask<String, Void, CharSequence> {
    @Override
    protected CharSequence doInBackground(String... urls) {
        Log.d("MINE", "doInBackground: got here");
        String urlString = urls[0];

        try {
            CharSequence result = HttpHelper.GetHttpResponse(urlString);
            return result;
        } catch (IOException ex) {
            cancel(true);
            String errorMessage = ex.getMessage() + "\n" + urlString;
            //TODO: Change tag to something meaningfull
            Log.e("MINE", errorMessage);
            return errorMessage;
        }

    }

}
