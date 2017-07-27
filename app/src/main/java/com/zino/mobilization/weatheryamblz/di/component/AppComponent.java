package com.zino.mobilization.weatheryamblz.di.component;

import com.zino.mobilization.weatheryamblz.di.module.AppModule;
import com.zino.mobilization.weatheryamblz.di.module.NetworkModule;
import com.zino.mobilization.weatheryamblz.model.prefs.SharedPreferencesHelper;
import com.zino.mobilization.weatheryamblz.presentation.presenter.SettingsPresenter;
import com.zino.mobilization.weatheryamblz.presentation.presenter.WeatherPresenter;
import com.zino.mobilization.weatheryamblz.ui.service.UpdateWeatherService;
import com.zino.mobilization.weatheryamblz.utils.AndroidJobHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Denis Buzmakov on 27.07.17.
 * <buzmakov.da@gmail.com>
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(WeatherPresenter presenter);
    void inject(SettingsPresenter presenter);
    void inject(UpdateWeatherService service);

    SharedPreferencesHelper getPreferenceHelper();
    AndroidJobHelper getJobHelper();
}
