package com.zino.mobilization.weatheryamblz.presenter.settings;

import com.zino.mobilization.weatheryamblz.presenter.settings.base.SettingsPresenterTest;

import org.junit.Test;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

/**
 * Created by Denis Buzmakov on 28.07.17.
 * <buzmakov.da@gmail.com>
 */

public class SwitchTemperatureTest extends SettingsPresenterTest {

    @Test
    public void shouldSetCelsius() {
        presenter.onCelsiusButtonClicked();
        verify(view, atLeastOnce()).setCelsiusButtonActive();
        verify(preferencesHelper, atLeastOnce()).setCelsius(true);
    }

    @Test
    public void shouldSetFahrenheit() {
        presenter.onFahrenheitButtonClicked();
        verify(view, atLeastOnce()).setFahrenheitButtonActive();
        verify(preferencesHelper, atLeastOnce()).setCelsius(false);
    }

}
