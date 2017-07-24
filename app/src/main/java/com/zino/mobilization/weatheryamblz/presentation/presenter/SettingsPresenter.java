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

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Алексей on 16.07.2017.
 */

@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsView> {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public SettingsPresenter() {
        boolean isCelsius = SharedPreferencesHelper.isCelsius(WeatherApplication.context);
        if (isCelsius) {
            getViewState().setCelsiusButtonActive();
        } else {
            getViewState().setFahrenheitButtonActive();
        }

        int id = SharedPreferencesHelper.getTimeRadioButtonId(WeatherApplication.context);
        if (id == 0) {
            id = R.id.radio_fifteen;
        }
        getViewState().checkRadioButton(id);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        compositeDisposable.add(
                SharedPreferencesHelper.getCurrentCity(WeatherApplication.context)
                .subscribe(city -> getViewState().setCurrentCityName(city.getName()))
        );
    }

    public void onCelsiusButtonClicked() {
        getViewState().setCelsiusButtonActive();
        SharedPreferencesHelper.setCelsius(WeatherApplication.context, true);
    }

    public void onFahrenheitButtonClicked() {
        getViewState().setFahrenheitButtonActive();
        SharedPreferencesHelper.setCelsius(WeatherApplication.context, false);
    }

    public void onCityClicked() {
        getViewState().openChooseCity();
    }

    public void onCityChosen(Place place) {
        LatLng latLngCity = place.getLatLng();
        City city = new City(place.getAddress().toString(), latLngCity.latitude, latLngCity.longitude);
        SharedPreferencesHelper.setCurrentCity(WeatherApplication.context, city);
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

        SharedPreferencesHelper.setUpdateTime(WeatherApplication.context, updateTime);
        SharedPreferencesHelper.setTimeRadioButtonId(WeatherApplication.context, id);
        if (updateTime == 0) {
            AndroidJobHelper.cancelAllJobs(WeatherApplication.context);
        } else {
            AndroidJobHelper.changeSchedulePeriod(WeatherApplication.context, updateTime);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
