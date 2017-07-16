package com.zino.mobilization.weatheryamblz.presentation.presenter;


import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.zino.mobilization.weatheryamblz.WeatherApplication;
import com.zino.mobilization.weatheryamblz.model.SharedPreferencesHelper;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;
import com.zino.mobilization.weatheryamblz.model.repository.OnCurrentWeatherLoadedListener;
import com.zino.mobilization.weatheryamblz.model.repository.WeatherRepository;
import com.zino.mobilization.weatheryamblz.model.repository.WeatherRepositoryImp;
import com.zino.mobilization.weatheryamblz.presentation.view.WeatherView;


@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> implements OnCurrentWeatherLoadedListener {

    private WeatherRepository weatherRepository;

    public WeatherPresenter() {
        weatherRepository = new WeatherRepositoryImp();

        weatherRepository.getCurrentWeather(524901, "ru", this);
        Log.i("ppppp", "WeatherPresenter: constructor");
        boolean isCelsium = SharedPreferencesHelper.isCelsius(WeatherApplication.context);
        if (isCelsium) {
            getViewState().setCelsius(true);
        } else {
            getViewState().setCelsius(false);
        }

    }


    @Override
    public void onCurrentWeatherLoaded(WeatherResponse weatherResponse) {
        getViewState().showWeather(weatherResponse);
    }

    @Override
    public void onError() {

    }

    public void onRefresh() {
        weatherRepository.getCurrentWeather(524901, "ru", this);
    }
}
