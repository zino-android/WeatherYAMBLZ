package com.zino.mobilization.weatheryamblz.presenter.weather;

import com.zino.mobilization.weatheryamblz.presenter.weather.base.WeatherPresenterTest;

import org.junit.Test;

import io.reactivex.subjects.PublishSubject;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Denis Buzmakov on 28.07.17.
 * <buzmakov.da@gmail.com>
 */

public class TemperatureUnitsTest extends WeatherPresenterTest {

    @Test
    public void shouldSetCelsius() {
        when(preferencesHelper.isCelsius()).thenReturn(true);
        when(preferencesHelper.getCurrentCity()).thenReturn(PublishSubject.create());//to ignore calling in onFirstViewAttach()
        presenter.attachView(view);
        verify(view, atLeastOnce()).setCelsius(true);
    }

    @Test
    public void shouldSetFahrenheit() {
        when(preferencesHelper.isCelsius()).thenReturn(false);
        when(preferencesHelper.getCurrentCity()).thenReturn(PublishSubject.create());//to ignore calling in onFirstViewAttach()
        presenter.attachView(view);
        verify(view, atLeastOnce()).setCelsius(false);
    }
}
