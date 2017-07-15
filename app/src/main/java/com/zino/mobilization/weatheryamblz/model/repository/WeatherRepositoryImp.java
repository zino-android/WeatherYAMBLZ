package com.zino.mobilization.weatheryamblz.model.repository;

import com.zino.mobilization.weatheryamblz.model.api.ApiInstance;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Алексей on 15.07.2017.
 */

public class WeatherRepositoryImp implements WeatherRepository {

    @Override
    public void getCurrentWeather(long cityId, String lang, OnCurrentWeatherLoadedListener listener) {
                 ApiInstance.getAPI()
                .getCurrentWeather(524901, "ad0dae19ea9cd24058581481b3ce84ce", "ru", "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //.doOnSubscribe(this::showRefresh)
                //.doAfterTerminate(this::hideRefresh)
                .subscribe(weatherResponse -> {weatherLoaded(weatherResponse, listener);},
                        throwable -> {onError(throwable, listener);});


    }



    private void weatherLoaded(WeatherResponse weatherResponse, OnCurrentWeatherLoadedListener listener) {
        listener.onCurrentWeatherLoaded(weatherResponse);
    }

    private void onError(Throwable throwable, OnCurrentWeatherLoadedListener listener) {
        throwable.printStackTrace();
        listener.onError();
    }
}
