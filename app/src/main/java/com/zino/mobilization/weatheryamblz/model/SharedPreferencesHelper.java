package com.zino.mobilization.weatheryamblz.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.google.gson.Gson;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.model.pojo.City;

import io.reactivex.Observable;

/**
 * Created by Алексей on 16.07.2017.
 */

public class SharedPreferencesHelper {

    private static final String FILE_NAME = "app_preferences";
    private static final String IS_CELSIUS_KEY = "is_celsius_key";
    private static final String UPDATE_TIME_KEY = "update_time_key";
    private static final String SELECTED_RADIO_KEY = "selected_radio_button_key";
    private static final String CURRENT_CITY = "current_city";

    public static boolean isCelsius(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getBoolean(IS_CELSIUS_KEY, true);
    }

    public static void setCelsius(Context context, boolean isCelsuis) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_CELSIUS_KEY, isCelsuis);
        editor.apply();

    }

    public static void setUpdateTime(Context context, long periodInMillis) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(UPDATE_TIME_KEY, periodInMillis);
        editor.apply();
    }

    public static long getUpdateTime(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getLong(UPDATE_TIME_KEY, 900_000L);
    }

    public static void setTimeRadioButtonId(Context context, int id) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SELECTED_RADIO_KEY, id);
        editor.apply();
    }

    public static int getTimeRadioButtonId(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getInt(SELECTED_RADIO_KEY, 0);
    }

    public static Observable<City> getCurrentCity(Context context) {
        RxSharedPreferences sharedPreferences = getRxSharedPreferences(context);
        Gson gson = new Gson();
        return sharedPreferences.getString(CURRENT_CITY, "")
                .asObservable()
                .map(s -> {
                    if(s.isEmpty()) {
                        return defaultCity(context);
                    } else {
                        return gson.fromJson(s, City.class);
                    }
                });
    }

    public static void setCurrentCity(Context context, City city) {
        if(city == null) return;
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        Gson gson = new Gson();
        String json = gson.toJson(city);
        sharedPreferences.edit()
                .putString(CURRENT_CITY, json)
                .apply();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(FILE_NAME,  Context.MODE_PRIVATE);
    }

    private static RxSharedPreferences getRxSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return RxSharedPreferences.create(sharedPreferences);
    }

    public static City defaultCity(Context context) {
        return new City(context.getString(R.string.moscow_full), 55.755826, 37.6173);
    }

}
