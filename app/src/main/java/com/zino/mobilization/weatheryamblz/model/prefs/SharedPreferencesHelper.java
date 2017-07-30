package com.zino.mobilization.weatheryamblz.model.prefs;

import com.zino.mobilization.weatheryamblz.model.pojo.City;

import io.reactivex.Observable;

/**
 * Created by Denis Buzmakov on 27.07.17.
 * <buzmakov.da@gmail.com>
 */

public interface SharedPreferencesHelper {

    boolean isCelsius();

    void setCelsius(boolean isCelsuis);

    void setUpdateTime(long periodInMillis);

    long getUpdateTime();

    void setTimeRadioButtonId(int id);

    int getTimeRadioButtonId();

    Observable<City> getCurrentCity();

    void setCurrentCity(City city);

    City defaultCity();

}
