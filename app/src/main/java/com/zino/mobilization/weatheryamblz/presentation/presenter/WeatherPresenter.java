package com.zino.mobilization.weatheryamblz.presentation.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.zino.mobilization.weatheryamblz.WeatherApplication;
import com.zino.mobilization.weatheryamblz.model.pojo.City;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;
import com.zino.mobilization.weatheryamblz.model.prefs.SharedPreferencesHelper;
import com.zino.mobilization.weatheryamblz.model.repository.WeatherRepository;
import com.zino.mobilization.weatheryamblz.presentation.view.WeatherView;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private City currentCity;

    @Inject
    WeatherRepository weatherRepository;

    @Inject
    SharedPreferencesHelper preferencesHelper;

    public WeatherPresenter() {
        WeatherApplication.getAppComponent().inject(this);
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
                            Observable<WeatherResponse> observable;
                            if(currentCity == null) {
                                observable = weatherRepository.getCurrentWeather(
                                        city.getLatitude(),
                                        city.getLongitude(),
                                        Locale.getDefault().getLanguage());
                            } else {
                                observable = weatherRepository.getCurrentWeatherFromApi(
                                        city.getLatitude(),
                                        city.getLongitude(),
                                        Locale.getDefault().getLanguage()).toObservable();
                            }
                            observable.doOnNext(result -> weatherRepository.saveCurrentWeather(result));
                            currentCity = city;
                            return observable;
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
