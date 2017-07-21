package com.zino.mobilization.weatheryamblz.model.pojo;


import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    private double temp;
    @SerializedName("pressure")
    private int pressure;
    @SerializedName("humidity")
    private int humidity;
    @SerializedName("temp_min")
    private double tempMin;
    @SerializedName("temp_max")
    private double tempMax;

    public double getTemp() {
        return temp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

}
