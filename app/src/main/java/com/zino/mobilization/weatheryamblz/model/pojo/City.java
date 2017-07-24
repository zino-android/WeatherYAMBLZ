package com.zino.mobilization.weatheryamblz.model.pojo;

/**
 * Created by Denis Buzmakov on 24.07.17.
 * <buzmakov.da@gmail.com>
 */

public class City {
    private String name;
    private double latitude;
    private double longitude;

    public City(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
