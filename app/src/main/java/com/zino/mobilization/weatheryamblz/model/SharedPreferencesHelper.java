package com.zino.mobilization.weatheryamblz.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Алексей on 16.07.2017.
 */

public class SharedPreferencesHelper {

    public static final String FILE_NAME = "app_preferences";
    private static final String IS_CELSIUS_KEY = "is_celsius_key";
    private static final String UPDATE_TIME_KEY = "update_time_key";
    private static final String SELECTED_RADIO_KEY = "selected_radio_button_key";

    public static boolean isCelsius(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME,  Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_CELSIUS_KEY, true);
    }

    public static void setCelsius(Context context, boolean isCelsuis) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME,  Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_CELSIUS_KEY, isCelsuis);
        editor.apply();

    }

    public static void setUpdateTime(Context context, long periodInMillis) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME,  Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(UPDATE_TIME_KEY, periodInMillis);
        editor.apply();
    }

    public static long getUpdateTime(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME,  Context.MODE_PRIVATE);
        return sharedPreferences.getLong(UPDATE_TIME_KEY, 900_000L);
    }

    public static void setTimeRadioButtonId(Context context, int id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME,  Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SELECTED_RADIO_KEY, id);
        editor.apply();
    }

    public static int getTimeRadioButtonId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME,  Context.MODE_PRIVATE);
        return sharedPreferences.getInt(SELECTED_RADIO_KEY, 0);
    }

}
