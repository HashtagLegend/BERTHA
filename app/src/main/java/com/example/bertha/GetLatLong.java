package com.example.bertha;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import static android.content.Context.LOCATION_SERVICE;

public class GetLatLong {

    private LocationManager locationManager;
    private LocationListener listener;
    public double getLatitude;
    public double getLongitude;



    public void getLatLong(){


        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                getLatitude = location.getLatitude();
                getLongitude = location.getLongitude();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
    }

    public double getGetLatitude() {
        return getLatitude;
    }

    public void setGetLatitude(double getLatitude) {
        this.getLatitude = getLatitude;
    }

    public double getGetLongitude() {
        return getLongitude;
    }

    public void setGetLongitude(double getLongitude) {
        this.getLongitude = getLongitude;
    }
}
