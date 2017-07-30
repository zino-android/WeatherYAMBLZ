package com.zino.mobilization.weatheryamblz.presenter.settings;

import com.zino.mobilization.weatheryamblz.model.pojo.City;
import com.zino.mobilization.weatheryamblz.presenter.settings.base.SettingsPresenterTest;
import com.zino.mobilization.weatheryamblz.common.TestData;

import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
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
        verify(viewState).openChooseCity();
    }

    @Test
    public void shouldChangeCity() {
        presenter.onCityChosen(TestData.getTestPlace());
        verify(preferencesHelper).setCurrentCity(any(City.class));
    }

    @Test
    public void shouldNotChangeCity() {
        presenter.onCityChosen(null);
        verify(preferencesHelper, never()).setCurrentCity(any(City.class));
    }
}
