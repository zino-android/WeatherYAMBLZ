package com.zino.mobilization.weatheryamblz.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.zino.mobilization.weatheryamblz.model.SharedPreferencesHelper;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;
import com.zino.mobilization.weatheryamblz.model.repository.OnCurrentWeatherLoadedListener;
import com.zino.mobilization.weatheryamblz.model.repository.WeatherRepository;
import com.zino.mobilization.weatheryamblz.model.repository.WeatherRepositoryImp;

import java.util.Locale;


public class UpdateWeatherService extends Service implements OnCurrentWeatherLoadedListener {

    public final static String WEATHER_LOADED_ACTION = "com.zino.mobilization.weather.weather_loaded";

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        loadWeather();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    void loadWeather() {
        WeatherRepository weatherRepository = new WeatherRepositoryImp();

        SharedPreferencesHelper.getCurrentCity(getApplicationContext())
                .subscribe(city -> weatherRepository.getCurrentWeather(
                        city.getLatitude(),
                        city.getLongitude(),
                        Locale.getDefault().getLanguage(),
                        this)
                );
    }

    @Override
    public void onCurrentWeatherLoaded(WeatherResponse weatherResponse) {
        Log.i("kkk", "onCurrentWeatherLoaded: "  + weatherResponse.getMain().getTemp() + " C");
        Intent intent = new Intent(WEATHER_LOADED_ACTION);
        sendBroadcast(intent);
        stopSelf();
    }

    @Override
    public void onError() {

    }
}
