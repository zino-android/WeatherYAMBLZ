package com.zino.mobilization.weatheryamblz;

import android.app.Application;
import android.content.Context;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.zino.mobilization.weatheryamblz.model.SharedPreferencesHelper;
import com.zino.mobilization.weatheryamblz.utils.AndroidJobHelper;



public class WeatherApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();



        long period = SharedPreferencesHelper.getUpdateTime(getApplicationContext());
        if (period == 0) {
            AndroidJobHelper.cancelAllJobs(getApplicationContext());
        } else {
            AndroidJobHelper.scheduleIfJobRequestsIsEmpty(getApplicationContext(), period);
        }

        context = getApplicationContext();

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);

            if (LeakCanary.isInAnalyzerProcess(this)) {
                return;
            }
            LeakCanary.install(this);
        }
    }
}
