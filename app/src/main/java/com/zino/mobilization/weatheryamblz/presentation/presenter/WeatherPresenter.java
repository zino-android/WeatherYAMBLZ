package com.zino.mobilization.weatheryamblz.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;
import com.zino.mobilization.weatheryamblz.model.repository.OnCurrentWeatherLoadedListener;
import com.zino.mobilization.weatheryamblz.model.repository.WeatherRepository;
import com.zino.mobilization.weatheryamblz.model.repository.WeatherRepositoryImp;
import com.zino.mobilization.weatheryamblz.presentation.view.WeatherView;

/**
 * Created by Алексей on 15.07.2017.
 */

@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> implements OnCurrentWeatherLoadedListener {

    private WeatherRepository weatherRepository;

    public WeatherPresenter() {
        weatherRepository = new WeatherRepositoryImp();
        //http://api.openweathermap.org/data/2.5/weather?id=524901&appid=ad0dae19ea9cd24058581481b3ce84ce&lang=ru&units=metric

        weatherRepository.getCurrentWeather(524901, "ru", this);


    }


    @Override
    public void onCurrentWeatherLoaded(WeatherResponse weatherResponse) {
        getViewState().showWeather(weatherResponse);

    }

    @Override
    public void onError() {

    }
}
