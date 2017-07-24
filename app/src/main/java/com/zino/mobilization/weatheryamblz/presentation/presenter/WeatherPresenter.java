package com.zino.mobilization.weatheryamblz.presentation.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.zino.mobilization.weatheryamblz.WeatherApplication;
import com.zino.mobilization.weatheryamblz.model.SharedPreferencesHelper;
import com.zino.mobilization.weatheryamblz.model.pojo.City;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;
import com.zino.mobilization.weatheryamblz.model.repository.OnCurrentWeatherLoadedListener;
import com.zino.mobilization.weatheryamblz.model.repository.WeatherRepository;
import com.zino.mobilization.weatheryamblz.model.repository.WeatherRepositoryImp;
import com.zino.mobilization.weatheryamblz.presentation.view.WeatherView;

import java.util.Locale;

import io.reactivex.disposables.CompositeDisposable;


@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> implements OnCurrentWeatherLoadedListener {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private WeatherRepository weatherRepository;
    private City currentCity;

    public WeatherPresenter() {
        weatherRepository = new WeatherRepositoryImp();


        boolean isCelsius = SharedPreferencesHelper.isCelsius(WeatherApplication.context);
        if (isCelsius) {
            getViewState().setCelsius(true);
        } else {
            getViewState().setCelsius(false);
        }

    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        compositeDisposable.add(
                SharedPreferencesHelper.getCurrentCity(WeatherApplication.context)
                        .subscribe(city -> {
                                    currentCity = city;
                                    weatherRepository.getCurrentWeather(
                                            city.getLatitude(),
                                            city.getLongitude(),
                                            Locale.getDefault().getLanguage(),
                                            this);
                                }
                        )
        );
    }

    @Override
    public void onCurrentWeatherLoaded(WeatherResponse weatherResponse) {
        getViewState().showWeather(weatherResponse);
        getViewState().showCity(currentCity);
    }

    @Override
    public void onError() {

    }

    public void onRefresh() {
        if(currentCity != null) {
            weatherRepository.getCurrentWeather(
                    currentCity.getLatitude(),
                    currentCity.getLongitude(),
                    Locale.getDefault().getLanguage(),
                    this);
        }
    }

    public void onWeatherLoadedFromService() {
        weatherRepository.getCurrentWeatherFromCache(this);
    }
}
