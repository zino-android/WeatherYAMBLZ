package com.zino.mobilization.weatheryamblz.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.zino.mobilization.weatheryamblz.WeatherApplication;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;
import com.zino.mobilization.weatheryamblz.model.prefs.SharedPreferencesHelper;
import com.zino.mobilization.weatheryamblz.model.repository.WeatherRepository;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class UpdateWeatherService extends Service {

    public final static String WEATHER_LOADED_ACTION = "com.zino.mobilization.weather.weather_loaded";

    private Disposable disposable;

    @Inject
    SharedPreferencesHelper preferencesHelper;

    @Inject
    WeatherRepository weatherRepository;

    public void onCreate() {
        super.onCreate();
        WeatherApplication.getAppComponent().inject(this);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        loadWeather();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    void loadWeather() {
        disposable = preferencesHelper.getCurrentCity()
                .flatMap(city -> weatherRepository.getCurrentWeather(
                        city.getLatitude(),
                        city.getLongitude(),
                        Locale.getDefault().getLanguage()))
                .doOnNext(result -> weatherRepository.saveCurrentWeather(result))
                .subscribeOn(Schedulers.io())
                .subscribe(this::onCurrentWeatherLoaded);
    }

    public void onCurrentWeatherLoaded(WeatherResponse weatherResponse) {
        Log.i("kkk", "onCurrentWeatherLoaded: "  + weatherResponse.getMain().getTemp() + " C");
        Intent intent = new Intent(WEATHER_LOADED_ACTION);
        sendBroadcast(intent);
        stopSelf();
    }
}
