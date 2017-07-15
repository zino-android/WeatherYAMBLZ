package com.zino.mobilization.weatheryamblz.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;
import com.zino.mobilization.weatheryamblz.model.repository.OnCurrentWeatherLoadedListener;
import com.zino.mobilization.weatheryamblz.model.repository.WeatherRepository;
import com.zino.mobilization.weatheryamblz.model.repository.WeatherRepositoryImp;

/**
 * Created by Алексей on 15.07.2017.
 */

public class UpdateWeatherService extends Service implements OnCurrentWeatherLoadedListener {

    public void onCreate() {
        super.onCreate();
        Log.i("kkk", "onCreate: ");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("kkk", "onStartCommand: ");
        loadWeather();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i("kkk", "onDestroy: ");
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    void loadWeather() {
       WeatherRepository weatherRepository = new WeatherRepositoryImp();
        //http://api.openweathermap.org/data/2.5/weather?id=524901&appid=ad0dae19ea9cd24058581481b3ce84ce&lang=ru&units=metric

        weatherRepository.getCurrentWeather(524901, "ru", this);
    }

    @Override
    public void onCurrentWeatherLoaded(WeatherResponse weatherResponse) {
        //TODO: send broadcast
        Log.i("kkk", "onCurrentWeatherLoaded: "  + weatherResponse.getMain().getTemp() + " C");
        stopSelf();
    }

    @Override
    public void onError() {

    }
}
