package com.zino.mobilization.weatheryamblz.model.repository;

import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;

/**
 * Created by Алексей on 15.07.2017.
 */

public interface OnCurrentWeatherLoadedListener {

    void onCurrentWeatherLoaded(WeatherResponse weatherResponse);

    void onError();
}
