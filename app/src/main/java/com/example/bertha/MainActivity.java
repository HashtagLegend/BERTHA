package com.example.bertha;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bertha.Model.Data;
import com.example.bertha.REST.ReadHttpTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String MINE = "MINE";
    public static final String urlWristbandData = "https://berthawristbandrestprovider.azurewebsites.net/api/wristbanddata/get.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void getData(View view) {
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Log.d(MINE, "doInBackground: clicked");
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(urlWristbandData)
                        .build();
                Response response = null;

                try{
                    response = client.newCall(request).execute();
                    return response.body().string();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                Log.d(MINE, "onPostExecute: " + o);
            }
        }.execute();

    }

    /*
    private class ReadFromWristbandTask extends ReadHttpTask {



        @Override
        protected void onPostExecute(CharSequence jsonString) {
            Log.d(MINE, "onPostExecute: called");
            Gson gson = new GsonBuilder().create();
            final Data data = gson.fromJson(jsonString.toString(), Data.class);


            ListView list = findViewById(R.id.mainDataLV);
            ArrayAdapter adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, (List<Data>) data);


            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //TODO: Create ArrayAdapter and create onItemClick method

                Intent intent = new Intent(getBaseContext(), BookActivity.class);
                Book book = (Book) parent.getItemAtPosition(position);
                intent.putExtra(BookActivity.BOOK, book);
                startActivity(intent);

                }

            });


            Log.d(MINE, "onPostExecute: " + data.toString());

        }

    }
    */
}
