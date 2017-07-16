package com.zino.mobilization.weatheryamblz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;
import com.zino.mobilization.weatheryamblz.ui.service.UpdateWeatherService;


public class WeatherSyncJob extends Job {

    public static final String TAG = "weather_update_tag";

    @Override
    @NonNull
    protected Result onRunJob(Params params) {
        Log.i("kkkkk", "onRunJob: ");
        if (params.getTag().equals(TAG)) {
            Intent intent = new Intent(getContext(), UpdateWeatherService.class);
            getContext().startService(intent);
        }

        return Result.SUCCESS;
    }

    public static void scheduleJob(long period) {
        new JobRequest.Builder(TAG)
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .setPeriodic(period, 300_000L)
                .setPersisted(false)
                .setUpdateCurrent(true)
                .build()
                .schedule();
    }

}
