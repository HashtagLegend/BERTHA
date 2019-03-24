package com.example.bertha.Model;

import java.io.Serializable;

public class Data implements Serializable {


    private int deviceId, co2, o3,  humidity;
    private double pm25, pm10, pressure, temperature;

    public Data(int deviceId, int co2, int o3, int humidity, double pm25, double pm10, double pressure, double temperature) {
        this.deviceId = deviceId;
        this.co2 = co2;
        this.o3 = o3;
        this.humidity = humidity;
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.pressure = pressure;
        this.temperature = temperature;
    }

    public Data() {
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

    @Override
    public String toString() {
        return "Data{" +
                "deviceId='" + deviceId + '\'' +
                ", pm25=" + pm25 +
                ", pm10=" + pm10 +
                ", co2=" + co2 +
                ", o3=" + o3 +
                ", pressure=" + pressure +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                '}';
    }
}
