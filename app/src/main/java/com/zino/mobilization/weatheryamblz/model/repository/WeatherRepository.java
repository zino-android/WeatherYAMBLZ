package com.zino.mobilization.weatheryamblz.model.repository;

/**
 * Created by Алексей on 15.07.2017.
 */

public interface WeatherRepository {

    void getCurrentWeather(long cityId, String lang, OnCurrentWeatherLoadedListener listener);

}
