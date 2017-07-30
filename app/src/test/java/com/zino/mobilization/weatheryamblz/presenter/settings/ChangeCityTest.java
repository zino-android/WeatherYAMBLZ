package com.zino.mobilization.weatheryamblz.presenter.settings;

import com.zino.mobilization.weatheryamblz.presenter.settings.base.SettingsPresenterTest;
import com.zino.mobilization.weatheryamblz.util.TestData;

import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by Denis Buzmakov on 28.07.17.
 * <buzmakov.da@gmail.com>
 */

public class ChangeCityTest extends SettingsPresenterTest {

    @Test
    public void shouldOpenChooseCity() {
        presenter.onCityClicked();
        verify(view, atLeastOnce()).openChooseCity();
    }

    @Test
    public void shouldChangeCity() {
        presenter.onCityChosen(TestData.getTestPlace());
        verify(preferencesHelper, atLeastOnce()).setCurrentCity(any());
    }

    @Test
    public void shouldNotChangeCity() {
        presenter.onCityChosen(null);
        verify(preferencesHelper, never()).setCurrentCity(any());
    }
}
