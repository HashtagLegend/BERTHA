package com.example.bertha.Model;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import java.io.Serializable;
import java.sql.Timestamp;

import static android.content.Context.LOCATION_SERVICE;

public class SendData implements Serializable {

    private LocationManager locationManager;
    private LocationListener listener;


    private Data data;
    private Long utc;
    private double latitude;
    private double longitude;
    private double noise;
    private String userId;

    public SendData(Data data, Long utc, double latitude,double longitude, double noise, String userId) {
        this.data = data;
        this.utc = utc;
        this.latitude = latitude;

        this.longitude = longitude;
        this.noise = noise;
        this.userId = userId;
    }

    public SendData() {

    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Long getUtc() {
        return utc;
    }

    public void setUtc(Long utc) {
        this.utc = utc;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getNoise() {
        return noise;
    }

    public void setNoise(double noise) {
        this.noise = noise;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SendData{" +
                "data=" + data +
                ", utc=" + utc +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", noise=" + noise +
                ", userId='" + userId + '\'' +
                '}';
    }
}

