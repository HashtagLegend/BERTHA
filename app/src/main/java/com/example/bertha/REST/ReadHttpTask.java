package com.example.bertha.REST;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;


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
