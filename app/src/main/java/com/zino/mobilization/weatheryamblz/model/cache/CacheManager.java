package com.zino.mobilization.weatheryamblz.model.cache;

import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;

import io.reactivex.Single;

/**
 * Created by Denis Buzmakov on 27.07.17.
 * <buzmakov.da@gmail.com>
 */

public interface CacheManager {
    boolean isCacheAvailable();
    Single<WeatherResponse> getCurrentWeather();
    void saveCurrentWeather(WeatherResponse response);
}
