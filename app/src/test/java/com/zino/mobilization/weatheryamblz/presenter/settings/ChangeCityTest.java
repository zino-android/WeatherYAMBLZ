package com.zino.mobilization.weatheryamblz.presenter.settings;

import android.net.Uri;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.zino.mobilization.weatheryamblz.presenter.settings.base.SettingsPresenterTest;

import org.junit.Test;

import java.util.List;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by Denis Buzmakov on 28.07.17.
 * <buzmakov.da@gmail.com>
 */

public class ChangeCityTest extends SettingsPresenterTest {
    private String testCityName = "Moscow, Russia";

    @Test
    public void shouldOpenChooseCity() {
        presenter.onCityClicked();
        verify(view, atLeastOnce()).openChooseCity();
    }

    @Test
    public void shouldChangeCity() {
        presenter.onCityChosen(getTestPlace());
        verify(view, atLeastOnce()).setCurrentCityName(testCityName);
    }

    @Test
    public void shouldNotChangeCity() {
        presenter.setPreferencesHelper(preferencesHelper);
        presenter.onCityChosen(null);
        verify(preferencesHelper, never()).setCurrentCity(any());
    }

    private Place getTestPlace() {
        return new Place() {
            @Override
            public String getId() {
                return null;
            }

            @Override
            public List<Integer> getPlaceTypes() {
                return null;
            }

            @Override
            public CharSequence getAddress() {
                return testCityName;
            }

            @Override
            public Locale getLocale() {
                return null;
            }

            @Override
            public CharSequence getName() {
                return null;
            }

            @Override
            public LatLng getLatLng() {
                return new LatLng(57, 58);
            }

            @Override
            public LatLngBounds getViewport() {
                return null;
            }

            @Override
            public Uri getWebsiteUri() {
                return null;
            }

            @Override
            public CharSequence getPhoneNumber() {
                return null;
            }

            @Override
            public float getRating() {
                return 0;
            }

            @Override
            public int getPriceLevel() {
                return 0;
            }

            @Override
            public CharSequence getAttributions() {
                return null;
            }

            @Override
            public Place freeze() {
                return null;
            }

            @Override
            public boolean isDataValid() {
                return false;
            }
        };
    }
}
