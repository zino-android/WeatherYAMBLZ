package com.zino.mobilization.weatheryamblz.model.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Алексей on 15.07.2017.
 */

public class Clouds {
    @SerializedName("all")
    private Integer all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }
}
