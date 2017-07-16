package com.zino.mobilization.weatheryamblz;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.zino.mobilization.weatheryamblz.model.SharedPreferencesHelper;
import com.zino.mobilization.weatheryamblz.utils.AndroidJobHelper;

import java.util.Set;

/**
 * Created by Алексей on 15.07.2017.
 */

public class WeatherApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        long period = SharedPreferencesHelper.getUpdateTime(getApplicationContext());
        if (period == 0) {
            AndroidJobHelper.cancelAllJobs(getApplicationContext());
        } else {
            AndroidJobHelper.scheduleIfJobRequestsIsEmpty(getApplicationContext(), period);
        }

        context = getApplicationContext();

        Stetho.initializeWithDefaults(this);
    }
}
