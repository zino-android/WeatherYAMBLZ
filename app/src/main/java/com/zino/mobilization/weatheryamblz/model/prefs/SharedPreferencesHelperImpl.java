package com.zino.mobilization.weatheryamblz.model.prefs;

import android.content.SharedPreferences;

import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.google.gson.Gson;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.model.pojo.City;
import com.zino.mobilization.weatheryamblz.utils.AppResources;

import io.reactivex.Observable;

/**
 * Created by Алексей on 16.07.2017.
 */

public class SharedPreferencesHelperImpl implements SharedPreferencesHelper{

    public  static final String FILE_NAME = "app_preferences";
    private static final String IS_CELSIUS_KEY = "is_celsius_key";
    private static final String UPDATE_TIME_KEY = "update_time_key";
    private static final String SELECTED_RADIO_KEY = "selected_radio_button_key";
    private static final String CURRENT_CITY = "current_city";

    private SharedPreferences sharedPreferences;
    private RxSharedPreferences rxSharedPreferences;
    private AppResources appResources;

    public SharedPreferencesHelperImpl(
            SharedPreferences sharedPreferences,
            RxSharedPreferences rxSharedPreferences,
            AppResources resources) {
        this.sharedPreferences = sharedPreferences;
        this.rxSharedPreferences = rxSharedPreferences;
        this.appResources = resources;
    }

    @Override
    public boolean isCelsius() {
        return sharedPreferences.getBoolean(IS_CELSIUS_KEY, true);
    }

    @Override
    public void setCelsius(boolean isCelsuis) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_CELSIUS_KEY, isCelsuis);
        editor.apply();

    }

    @Override
    public void setUpdateTime(long periodInMillis) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(UPDATE_TIME_KEY, periodInMillis);
        editor.apply();
    }

    @Override
    public long getUpdateTime() {
        return sharedPreferences.getLong(UPDATE_TIME_KEY, 900_000L);
    }

    @Override
    public void setTimeRadioButtonId(int id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SELECTED_RADIO_KEY, id);
        editor.apply();
    }

    @Override
    public int getTimeRadioButtonId() {
        return sharedPreferences.getInt(SELECTED_RADIO_KEY, 0);
    }

    @Override
    public Observable<City> getCurrentCity() {
        Gson gson = new Gson();
        return rxSharedPreferences.getString(CURRENT_CITY, "")
                .asObservable()
                .map(s -> {
                    if(s.isEmpty()) {
                        return defaultCity();
                    } else {
                        return gson.fromJson(s, City.class);
                    }
                });
    }

    @Override
    public void setCurrentCity(City city) {
        if(city == null) return;
        Gson gson = new Gson();
        String json = gson.toJson(city);
        sharedPreferences.edit()
                .putString(CURRENT_CITY, json)
                .apply();
    }

    @Override
    public City defaultCity() {
        return new City(appResources.getString(R.string.moscow_full), 55.755826, 37.6173);
    }

}
