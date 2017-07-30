package com.zino.mobilization.weatheryamblz.presenter.weather;

import com.zino.mobilization.weatheryamblz.model.pojo.City;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;
import com.zino.mobilization.weatheryamblz.presenter.weather.base.WeatherPresenterTest;
import com.zino.mobilization.weatheryamblz.util.RxImmediateSchedulerRule;

import org.junit.ClassRule;
import org.junit.Test;

import java.util.Locale;

import io.reactivex.observers.TestObserver;
import io.reactivex.subjects.PublishSubject;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Denis Buzmakov on 28.07.17.
 * <buzmakov.da@gmail.com>
 */

public class LoadingWeatherTest extends WeatherPresenterTest {

    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    @Test
    public void shouldLoadFromApiAfterRefresh() {
        presenter.attachView(view);

        when(weatherRepository.getCurrentWeatherFromApi(anyDouble(), anyDouble(), anyString()))
                .thenReturn(singleSubject);
        presenter.setCurrentCity(currentCity);

        presenter.onRefresh();

        verify(weatherRepository, atLeastOnce()).getCurrentWeatherFromApi(anyDouble(), anyDouble(), anyString());
        verify(weatherRepository, never()).getCurrentWeather(anyDouble(), anyDouble(), anyString());
        verify(weatherRepository, never()).getCurrentWeatherFromCache();

        singleSubject.onSuccess(weatherResponse);
        responseObserver.assertValue(weatherResponse);

        verify(weatherRepository, atLeastOnce()).saveCurrentWeather(weatherResponse);
        verifyShowWeather(weatherResponse);
    }

    @Test
    public void shouldLoadFromCache() {
        presenter.attachView(view);

        when(weatherRepository.getCurrentWeatherFromCache())
                .thenReturn(singleSubject);
        presenter.setCurrentCity(currentCity);

        presenter.onWeatherLoadedFromService();

        verify(weatherRepository, never()).getCurrentWeatherFromApi(anyDouble(), anyDouble(), anyString());
        verify(weatherRepository, never()).getCurrentWeather(anyDouble(), anyDouble(), anyString());
        verify(weatherRepository, atLeastOnce()).getCurrentWeatherFromCache();

        singleSubject.onSuccess(weatherResponse);
        responseObserver.assertValue(weatherResponse);

        verify(weatherRepository, never()).saveCurrentWeather(weatherResponse);
        verifyShowWeather(weatherResponse);
    }

    @Test
    public void shouldLoadOnStartup() {
        PublishSubject<WeatherResponse> weatherSubject = PublishSubject.create();
        PublishSubject<City> citySubject = PublishSubject.create();
        TestObserver<WeatherResponse> responseObserver = new TestObserver<>();
        weatherSubject.subscribe(responseObserver);

        when(preferencesHelper.getCurrentCity()).thenReturn(citySubject);
        when(weatherRepository.getCurrentWeather(anyDouble(), anyDouble(), anyString()))
                .thenReturn(weatherSubject);

        presenter.attachView(view);

        verify(preferencesHelper, atLeastOnce()).getCurrentCity();
        City city = new City("", 55, 51);

        citySubject.onNext(city);
        verify(weatherRepository, atLeastOnce())
                .getCurrentWeather(city.getLatitude(), city.getLongitude(), Locale.getDefault().getLanguage());
        weatherSubject.onNext(weatherResponse);
        responseObserver.assertValue(weatherResponse);

        verify(weatherRepository, atLeastOnce()).saveCurrentWeather(weatherResponse);
        verifyShowWeather(weatherResponse);
    }

    private void verifyShowWeather(WeatherResponse response) {
        verify(view, atLeastOnce()).hideLoading();
        verify(view, atLeastOnce()).showWeather(response);
        verify(view, atLeastOnce()).showCity(any());
    }

}
