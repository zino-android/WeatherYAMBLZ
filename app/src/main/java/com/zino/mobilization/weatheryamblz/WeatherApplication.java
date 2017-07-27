package com.zino.mobilization.weatheryamblz;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.zino.mobilization.weatheryamblz.di.component.AppComponent;
import com.zino.mobilization.weatheryamblz.di.component.DaggerAppComponent;
import com.zino.mobilization.weatheryamblz.di.module.AppModule;
import com.zino.mobilization.weatheryamblz.di.module.NetworkModule;

import timber.log.Timber;


public class WeatherApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();

        long period = appComponent.getPreferenceHelper().getUpdateTime();
        if (period == 0) {
            appComponent.getJobHelper().cancelAllJobs();
        } else {
            appComponent.getJobHelper().scheduleIfJobRequestsIsEmpty(period);
        }

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
            Timber.plant(new Timber.DebugTree());

            if (LeakCanary.isInAnalyzerProcess(this)) {
                return;
            }
            LeakCanary.install(this);
        }
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
