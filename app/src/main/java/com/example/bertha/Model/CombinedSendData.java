package com.example.bertha.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CombinedSendData implements Serializable {

    @SerializedName("deviceId")
    private int deviceId;
    @SerializedName("co2")
    private int co2;
    @SerializedName("o3")
    private int o3;
    @SerializedName("humidity")
    private int humidity;

    @SerializedName("pm25")
    private double pm25;
    @SerializedName("10")
    private double pm10;
    @SerializedName("")
    private double pressure;
    @SerializedName("temperature")
    private double temperature;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("noise")
    private int noise;
    @SerializedName("utc")
    private Long utc;
    @SerializedName("userId")
    private String userId;

    public CombinedSendData(int deviceId, int co2, int o3, int humidity, double pm25, double pm10, double pressure, double temperature, Long utc, double latitude, double longitude, int noise,  String userId) {
        this.deviceId = deviceId;
        this.co2 = co2;
        this.o3 = o3;
        this.humidity = humidity;
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.pressure = pressure;
        this.temperature = temperature;
        this.latitude = latitude;
        this.longitude = longitude;
        this.noise = noise;
        this.utc = utc;
        this.userId = userId;
    }

    public CombinedSendData() {
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getO3() {
        return o3;
    }

    public void setO3(int o3) {
        this.o3 = o3;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getPm25() {
        return pm25;
    }

    public void setPm25(double pm25) {
        this.pm25 = pm25;
    }

    public double getPm10() {
        return pm10;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
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

    public int getNoise() {
        return noise;
    }

    public void setNoise(int noise) {
        this.noise = noise;
    }

    public Long getUtc() {
        return utc;
    }

    public void setUtc(Long utc) {
        this.utc = utc;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CombinedSendData{" +
                "deviceId=" + deviceId +
                ", co2=" + co2 +
                ", o3=" + o3 +
                ", humidity=" + humidity +
                ", pm25=" + pm25 +
                ", pm10=" + pm10 +
                ", pressure=" + pressure +
                ", temperature=" + temperature +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", noise=" + noise +
                ", utc=" + utc +
                ", userId='" + userId + '\'' +
                '}';
    }
}
