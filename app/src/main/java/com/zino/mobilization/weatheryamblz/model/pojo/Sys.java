package com.zino.mobilization.weatheryamblz.model.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Алексей on 15.07.2017.
 */

public class Sys {

    @SerializedName("type")
    private Integer type;
    @SerializedName("id")
    private Integer id;
    @SerializedName("message")
    private Double message;
    @SerializedName("country")
    private String country;
    @SerializedName("sunrise")
    private Integer sunrise;
    @SerializedName("sunset")
    private Integer sunset;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

}
