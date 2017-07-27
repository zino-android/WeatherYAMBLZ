package com.zino.mobilization.weatheryamblz.model.repository;

import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;

import io.reactivex.Observable;

/**
 * Created by Алексей on 15.07.2017.
 */

public interface WeatherRepository {

    Observable<WeatherResponse> getCurrentWeather(double lat, double lon, String lang);

    Observable<WeatherResponse> getCurrentWeatherFromCache();

}
