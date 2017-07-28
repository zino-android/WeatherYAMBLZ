package com.zino.mobilization.weatheryamblz.presenter.weather;

import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;
import com.zino.mobilization.weatheryamblz.presenter.weather.base.WeatherPresenterTest;

import org.junit.Test;

import io.reactivex.observers.TestObserver;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.SingleSubject;

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

    @Test
    public void shouldLoadFromApiAfterRefresh() {
        presenter.attachView(view);
        SingleSubject<WeatherResponse> subject = SingleSubject.create();
        TestObserver<WeatherResponse> responseObserver = new TestObserver<>();
        subject.subscribe(responseObserver);

        when(weatherRepository.getCurrentWeatherFromApi(anyDouble(), anyDouble(), anyString()))
                .thenReturn(subject);
        when(preferencesHelper.getCurrentCity()).thenReturn(PublishSubject.create());//to ignore calling in onFirstViewAttach()
        presenter.setWeatherRepository(weatherRepository);
        presenter.setCurrentCity(currentCity);

        presenter.onRefresh();

        verify(weatherRepository, atLeastOnce()).getCurrentWeatherFromApi(anyDouble(), anyDouble(), anyString());
        verify(weatherRepository, never()).getCurrentWeather(anyDouble(), anyDouble(), anyString());
        verify(weatherRepository, never()).getCurrentWeatherFromCache();

        subject.onSuccess(weatherResponse);
        responseObserver.assertValue(weatherResponse);

        verify(weatherRepository, atLeastOnce()).saveCurrentWeather(weatherResponse);
        verifyShowWeather(weatherResponse);
    }

    @Test
    public void shouldLoadFromCache() {
        presenter.attachView(view);
        SingleSubject<WeatherResponse> subject = SingleSubject.create();
        TestObserver<WeatherResponse> responseObserver = new TestObserver<>();
        subject.subscribe(responseObserver);

        when(weatherRepository.getCurrentWeatherFromCache())
                .thenReturn(subject);
        when(preferencesHelper.getCurrentCity()).thenReturn(PublishSubject.create());//to ignore calling in onFirstViewAttach()
        presenter.setWeatherRepository(weatherRepository);
        presenter.setCurrentCity(currentCity);

        presenter.onWeatherLoadedFromService();

        verify(weatherRepository, never()).getCurrentWeatherFromApi(anyDouble(), anyDouble(), anyString());
        verify(weatherRepository, never()).getCurrentWeather(anyDouble(), anyDouble(), anyString());
        verify(weatherRepository, atLeastOnce()).getCurrentWeatherFromCache();

        subject.onSuccess(weatherResponse);
        responseObserver.assertValue(weatherResponse);

        verify(weatherRepository, never()).saveCurrentWeather(weatherResponse);
        verifyShowWeather(weatherResponse);
    }

    private void verifyShowWeather(WeatherResponse response) {
        verify(view, atLeastOnce()).hideLoading();
        verify(view, atLeastOnce()).showWeather(response);
        verify(view, atLeastOnce()).showCity(any());
    }

}
