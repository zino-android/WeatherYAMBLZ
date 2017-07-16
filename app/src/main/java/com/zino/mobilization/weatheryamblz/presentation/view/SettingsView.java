package com.zino.mobilization.weatheryamblz.presentation.view;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Алексей on 16.07.2017.
 */

public interface SettingsView extends MvpView {

    void setFahrenheitButtonActive();

    void setCelsiusButtonActive();

    void checkRadioButton(int id);

}
