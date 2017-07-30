package com.zino.mobilization.weatheryamblz.presenter.weather;

import com.zino.mobilization.weatheryamblz.model.pojo.City;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;
import com.zino.mobilization.weatheryamblz.presenter.weather.base.WeatherPresenterTest;
import com.zino.mobilization.weatheryamblz.common.TestData;

import org.junit.Test;

import java.util.Locale;

import io.reactivex.observers.TestObserver;
import io.reactivex.subjects.PublishSubject;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Denis Buzmakov on 28.07.17.
 * <buzmakov.da@gmail.com>
 */

public class LoadingWeatherTest extends WeatherPresenterTest {

    @Test
    public void shouldLoadFromApiAfterRefresh() {
        when(weatherRepository.getCurrentWeatherFromApi(anyDouble(), anyDouble(), anyString()))
                .thenReturn(singleSubject);
        presenter.setCurrentCity(currentCity);

        presenter.onRefresh();

        verify(weatherRepository, only()).getCurrentWeatherFromApi(anyDouble(), anyDouble(), anyString());

        singleSubject.onSuccess(weatherResponse);
        responseObserver.assertValue(weatherResponse);

        verify(weatherRepository).saveCurrentWeather(weatherResponse);
        verifyShowWeather(weatherResponse);
    }

    @Test
    public void shouldLoadFromCacheAfterLoadingFromService() {
        when(weatherRepository.getCurrentWeatherFromCache())
                .thenReturn(singleSubject);
        presenter.setCurrentCity(currentCity);

        presenter.onWeatherLoadedFromService();

        verify(weatherRepository, only()).getCurrentWeatherFromCache();

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

        presenter.onFirstViewAttach();

        verify(preferencesHelper).getCurrentCity();
        City city = TestData.getTestCity();

        citySubject.onNext(city);
        verify(weatherRepository, only())
                .getCurrentWeather(city.getLatitude(), city.getLongitude(), Locale.getDefault().getLanguage());
        weatherSubject.onNext(weatherResponse);
        responseObserver.assertValue(weatherResponse);

        verify(weatherRepository).saveCurrentWeather(weatherResponse);
        verifyShowWeather(weatherResponse);
    }

    private void verifyShowWeather(WeatherResponse response) {
        verify(viewState).hideLoading();
        verify(viewState).showWeather(response);
        verify(viewState).showCity(any(City.class));
    }

}
