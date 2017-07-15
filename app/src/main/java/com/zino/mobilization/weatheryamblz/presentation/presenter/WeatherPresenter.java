package com.zino.mobilization.weatheryamblz.presentation.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
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

    }


    @Override
    public void onCurrentWeatherLoaded(WeatherResponse weatherResponse) {
        getViewState().showWeather(weatherResponse);
    }

    @Override
    public void onError() {

    }
}
