package com.example.bertha.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bertha.Model.CombinedSendData;
import com.example.bertha.R;

import java.util.List;

public class DataListItemAdapter extends ArrayAdapter<CombinedSendData> {
    private final int resource;

    public DataListItemAdapter(Context context, int resource, List<CombinedSendData> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    public DataListItemAdapter(Context context, int resource, CombinedSendData[] objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        CombinedSendData data = getItem(position);

        String userId = data.getUserId();
        int deviceId = data.getDeviceId();
        Long time = data.getUtc();
        LinearLayout dataView;

        if (convertView == null) {
            dataView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(inflater);
            li.inflate(resource, dataView, true);
        } else {
            dataView = (LinearLayout) convertView;
        }
        TextView userIdView = dataView.findViewById(R.id.dataListItem_userId);
        TextView deviceIdView = dataView.findViewById(R.id.dataListItem_deviceId);
        TextView utcView = dataView.findViewById(R.id.dataListItem_utc);
        userIdView.setText("User Id: " + userId);
        deviceIdView.setText("Device ID: " + deviceId);
        utcView.setText("Time: " + time);
        return dataView;
    }

}
