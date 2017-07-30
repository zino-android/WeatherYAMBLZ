package com.zino.mobilization.weatheryamblz.presenter.settings.base;

import com.arellomobile.mvp.InjectViewState;
import com.zino.mobilization.weatheryamblz.model.prefs.SharedPreferencesHelper;
import com.zino.mobilization.weatheryamblz.presentation.presenter.SettingsPresenter;
import com.zino.mobilization.weatheryamblz.utils.AndroidJobHelper;

/**
 * Created by Denis Buzmakov on 30.07.17.
 * <buzmakov.da@gmail.com>
 */

@InjectViewState
public class TestPresenter extends SettingsPresenter {
    public TestPresenter(SharedPreferencesHelper preferencesHelper, AndroidJobHelper jobHelper) {
        super(preferencesHelper, jobHelper);
    }
}
