package com.zino.mobilization.weatheryamblz.presentation.view;

import com.arellomobile.mvp.MvpView;



public interface SettingsView extends MvpView {

    void setFahrenheitButtonActive();

    void setCelsiusButtonActive();

    void checkRadioButton(int id);

}
