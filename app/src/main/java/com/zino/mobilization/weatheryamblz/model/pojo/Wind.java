package com.zino.mobilization.weatheryamblz.model.pojo;


import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    private double speed;
    @SerializedName("deg")
    private double deg;

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }

}
