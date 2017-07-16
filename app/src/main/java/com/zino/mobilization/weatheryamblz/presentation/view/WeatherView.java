package com.zino.mobilization.weatheryamblz.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;

/**
 * Created by Алексей on 14.07.2017.
 */

public interface WeatherView extends MvpView {

    void showWeather(WeatherResponse weatherResponse);

    void showLoading();

    void hideLoading();

    void setCelsius(boolean celsius);

}
