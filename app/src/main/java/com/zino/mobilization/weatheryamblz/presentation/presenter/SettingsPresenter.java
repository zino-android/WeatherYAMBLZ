package com.zino.mobilization.weatheryamblz.presentation.presenter;

import android.app.Application;
import android.support.annotation.IdRes;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.WeatherApplication;
import com.zino.mobilization.weatheryamblz.model.SharedPreferencesHelper;
import com.zino.mobilization.weatheryamblz.model.pojo.Weather;
import com.zino.mobilization.weatheryamblz.presentation.view.SettingsView;
import com.zino.mobilization.weatheryamblz.utils.AndroidJobHelper;

/**
 * Created by Алексей on 16.07.2017.
 */

@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsView> {

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


    public void onCelsiusButtonClicked() {
        getViewState().setCelsiusButtonActive();
        SharedPreferencesHelper.setCelsius(WeatherApplication.context, true);
    }

    public void onFahrenheitButtonClicked() {
        getViewState().setFahrenheitButtonActive();
        SharedPreferencesHelper.setCelsius(WeatherApplication.context, false);
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



}
