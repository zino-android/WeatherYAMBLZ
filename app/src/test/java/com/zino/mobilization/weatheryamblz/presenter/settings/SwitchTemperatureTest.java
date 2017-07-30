package com.zino.mobilization.weatheryamblz.presenter.settings;

import com.zino.mobilization.weatheryamblz.presenter.settings.base.SettingsPresenterTest;

import org.junit.Test;

import static org.mockito.Mockito.verify;

/**
 * Created by Denis Buzmakov on 28.07.17.
 * <buzmakov.da@gmail.com>
 */

public class SwitchTemperatureTest extends SettingsPresenterTest {

    @Test
    public void shouldSetCelsius() {
        presenter.onCelsiusButtonClicked();
        verify(viewState).setCelsiusButtonActive();
        verify(preferencesHelper).setCelsius(true);
    }

    @Test
    public void shouldSetFahrenheit() {
        presenter.onFahrenheitButtonClicked();
        verify(viewState).setFahrenheitButtonActive();
        verify(preferencesHelper).setCelsius(false);
    }

}
