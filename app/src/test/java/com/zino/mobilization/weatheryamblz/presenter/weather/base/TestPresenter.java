package com.zino.mobilization.weatheryamblz.presenter.weather.base;

import com.arellomobile.mvp.InjectViewState;
import com.zino.mobilization.weatheryamblz.model.pojo.City;
import com.zino.mobilization.weatheryamblz.model.prefs.SharedPreferencesHelper;
import com.zino.mobilization.weatheryamblz.model.repository.WeatherRepository;
import com.zino.mobilization.weatheryamblz.presentation.presenter.WeatherPresenter;

/**
 * Created by Denis Buzmakov on 28.07.17.
 * <buzmakov.da@gmail.com>
 */

@InjectViewState
public class TestPresenter extends WeatherPresenter {

    TestPresenter(SharedPreferencesHelper preferencesHelper, WeatherRepository weatherRepository) {
        super(preferencesHelper, weatherRepository);
    }

    public void setCurrentCity(City city) {
        this.currentCity = city;
    }
}
