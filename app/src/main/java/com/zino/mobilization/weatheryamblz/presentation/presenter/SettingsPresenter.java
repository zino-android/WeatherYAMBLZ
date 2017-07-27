package com.zino.mobilization.weatheryamblz.presentation.presenter;

import android.support.annotation.IdRes;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.WeatherApplication;
import com.zino.mobilization.weatheryamblz.model.SharedPreferencesHelper;
import com.zino.mobilization.weatheryamblz.model.pojo.City;
import com.zino.mobilization.weatheryamblz.presentation.view.SettingsView;
import com.zino.mobilization.weatheryamblz.utils.AndroidJobHelper;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Алексей on 16.07.2017.
 */

@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsView> {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    SharedPreferencesHelper preferencesHelper;

    @Inject
    AndroidJobHelper jobHelper;

    public SettingsPresenter() {
        WeatherApplication.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        boolean isCelsius = preferencesHelper.isCelsius();
        if (isCelsius) {
            getViewState().setCelsiusButtonActive();
        } else {
            getViewState().setFahrenheitButtonActive();
        }

        int id = preferencesHelper.getTimeRadioButtonId();
        if (id == 0) {
            id = R.id.radio_fifteen;
        }
        getViewState().checkRadioButton(id);

        compositeDisposable.add(
                preferencesHelper.getCurrentCity()
                .subscribe(city -> getViewState().setCurrentCityName(city.getName()))
        );
    }

    public void onCelsiusButtonClicked() {
        getViewState().setCelsiusButtonActive();
        preferencesHelper.setCelsius(true);
    }

    public void onFahrenheitButtonClicked() {
        getViewState().setFahrenheitButtonActive();
        preferencesHelper.setCelsius(false);
    }

    public void onCityClicked() {
        getViewState().openChooseCity();
    }

    public void onCityChosen(Place place) {
        LatLng latLngCity = place.getLatLng();
        City city = new City(place.getAddress().toString(), latLngCity.latitude, latLngCity.longitude);
        preferencesHelper.setCurrentCity(city);
    }

    public void onTimeCheckedChanged(@IdRes int id) {
        long updateTime = 0;
        switch (id) {
            case R.id.radio_manually:
                updateTime = 0;
                break;
            case R.id.radio_fifteen:
                updateTime = 900_000L;
                break;
            case R.id.radio_thirteen:
                updateTime = 1_800_000L;
                break;
            case R.id.radio_one_hour:
                updateTime = 3_600_000L;
                break;
            case R.id.radio_three_hour:
                updateTime = 10_800_000L;
                break;
        }

        preferencesHelper.setUpdateTime(updateTime);
        preferencesHelper.setTimeRadioButtonId(id);
        if (updateTime == 0) {
            jobHelper.cancelAllJobs();
        } else {
            jobHelper.changeSchedulePeriod(updateTime);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
