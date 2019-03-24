package com.example.bertha.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bertha.Model.CombinedSendData;
import com.example.bertha.R;
import com.example.bertha.REST.ReadHttpTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ShowAllDataActivity extends AppCompatActivity {

    public static final String getAllDataUrl = "https://berthabackendrestprovider.azurewebsites.net/api/data/patr/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_data);

        TextView listHeader = new TextView(this);
        listHeader.setText("Data Registreret");
        listHeader.setTextAppearance(this, android.R.style.TextAppearance_Large);
        ListView listView = findViewById(R.id.showAllDataListView);
        listView.addHeaderView(listHeader);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ReadAllDataTask dataTask = new ReadAllDataTask();
        dataTask.execute(getAllDataUrl);
    }

    private class ReadAllDataTask extends ReadHttpTask {
        @Override
        protected void onPostExecute(CharSequence jsonString) {
            Gson gson = new GsonBuilder().create();
            final CombinedSendData[] allData = gson.fromJson(jsonString.toString(), CombinedSendData[].class);

            ListView listView = findViewById(R.id.showAllDataListView);
            ArrayAdapter<CombinedSendData> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, allData);
            //BookListItemAdapter adapter = new BookListItemAdapter(getBaseContext(), R.layout.booklist_item, books);
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
        }

        @Override
        protected void onCancelled(CharSequence message) {
            Log.e("MINE", message.toString());
        }
    }

}
