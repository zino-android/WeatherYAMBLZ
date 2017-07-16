package com.zino.mobilization.weatheryamblz;

import android.app.Application;
import android.content.Context;

import com.evernote.android.job.JobManager;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

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

        JobManager.create(this).addJobCreator(new WeatherJobCreator());
        WeatherSyncJob.scheduleJob();
        context = getApplicationContext();

        Stetho.initializeWithDefaults(this);
    }
}
