package com.zino.mobilization.weatheryamblz.presentation.view;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Алексей on 14.07.2017.
 */

public interface WeatherView extends MvpView {

    void showWeather();

    void showLoading();

    void hideLoading();

}
