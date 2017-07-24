package com.zino.mobilization.weatheryamblz.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface SettingsView extends MvpView {

    void setFahrenheitButtonActive();

    void setCelsiusButtonActive();

    void checkRadioButton(int id);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openChooseCity();

    void setCurrentCityName(String name);

}
