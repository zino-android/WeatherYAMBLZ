package com.zino.mobilization.weatheryamblz.presentation.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.zino.mobilization.weatheryamblz.model.pojo.City;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;
import com.zino.mobilization.weatheryamblz.model.prefs.SharedPreferencesHelper;
import com.zino.mobilization.weatheryamblz.model.repository.WeatherRepository;
import com.zino.mobilization.weatheryamblz.presentation.view.WeatherView;

import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected City currentCity;
    private WeatherRepository weatherRepository;
    protected SharedPreferencesHelper preferencesHelper;

    public WeatherPresenter(SharedPreferencesHelper preferencesHelper,
                            WeatherRepository weatherRepository) {
        this.preferencesHelper = preferencesHelper;
        this.weatherRepository = weatherRepository;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        boolean isCelsius = preferencesHelper.isCelsius();
        if (isCelsius) {
            getViewState().setCelsius(true);
        } else {
            getViewState().setCelsius(false);
        }
        compositeDisposable.add(
                preferencesHelper.getCurrentCity()
                        .flatMap(city -> {
                            currentCity = city;
                            return weatherRepository.getCurrentWeather(
                                    city.getLatitude(),
                                    city.getLongitude(),
                                    Locale.getDefault().getLanguage())
                                    .doOnNext(result -> weatherRepository.saveCurrentWeather(result));
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::showWeatherResponse)
        );
    }

    public void onRefresh() {
        if(currentCity != null) {
            compositeDisposable.add(
                    weatherRepository.getCurrentWeatherFromApi(
                            currentCity.getLatitude(),
                            currentCity.getLongitude(),
                            Locale.getDefault().getLanguage())
                            .doOnSuccess(result -> weatherRepository.saveCurrentWeather(result))
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(this::showWeatherResponse)
            );
        }
    }

    public void onWeatherLoadedFromService() {
        compositeDisposable.add(
                weatherRepository.getCurrentWeatherFromCache()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::showWeatherResponse)
        );
    }

    private void showWeatherResponse(WeatherResponse response) {
        getViewState().hideLoading();
        getViewState().showWeather(response);
        getViewState().showCity(currentCity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
