package com.example.bertha.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bertha.Adapters.DataListItemAdapter;
import com.example.bertha.HelperClasses.OnSwipeTouchListener;
import com.example.bertha.Model.CombinedSendData;
import com.example.bertha.R;
import com.example.bertha.REST.ReadHttpTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ShowAllDataActivity extends AppCompatActivity {

    private int counter;
    private ListView listView;
    private TextView listHeader;

    //Toolbar
    private Toolbar toolbar;




    public static final String getAllDataUrl = "https://berthabackendrestprovider.azurewebsites.net/api/data/pady/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_data);
        listHeader = new TextView(this);
        listHeader.setTextAppearance(this, android.R.style.TextAppearance_Large);
        listView = findViewById(R.id.showAllDataListView);
        //Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView.addHeaderView(listHeader);


        listView.setOnTouchListener(new OnSwipeTouchListener(ShowAllDataActivity.this) {
            public void onSwipeTop() {

            }

            public void onSwipeRight() {
                finish();
            }

            public void onSwipeLeft() {

            }

            public void onSwipeBottom() {
                ReadAllDataTask dataTask = new ReadAllDataTask();
                dataTask.execute(getAllDataUrl);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        ReadAllDataTask dataTask = new ReadAllDataTask();
        dataTask.execute(getAllDataUrl);
    }

    //Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.actionBarUserSettings) {
            Intent intent = new Intent(this, UserSettings.class);
            startActivity(intent);
        }
        else if(id == R.id.actionBarLogOut){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.actionBarSettings){

        }

        return super.onOptionsItemSelected(item);
    }



    private class ReadAllDataTask extends ReadHttpTask {
        @Override
        protected void onPostExecute(CharSequence jsonString) {
            Gson gson = new GsonBuilder().create();
            final CombinedSendData[] allData = gson.fromJson(jsonString.toString(), CombinedSendData[].class);

            ListView listView = findViewById(R.id.showAllDataListView);
            //ArrayAdapter<CombinedSendData> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, allData);
            DataListItemAdapter adapter = new DataListItemAdapter(getBaseContext(), R.layout.data_list_item, allData);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getBaseContext(), DataActivity.class);
                    CombinedSendData data = (CombinedSendData) parent.getItemAtPosition(position);
                    intent.putExtra(DataActivity.DATA, data);

                    startActivity(intent);
                }
            });
            counter = allData.length;
            listHeader.setText("Antal målinger registreret: " + counter);
        }

        @Override
        protected void onCancelled(CharSequence message) {
            Log.e("MINE", message.toString());
        }
    }

}
