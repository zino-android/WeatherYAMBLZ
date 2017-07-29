package com.zino.mobilization.weatheryamblz;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * Created by Алексей on 15.07.2017.
 */

public class WeatherJobCreator implements JobCreator {
    @Override
    public Job create(String tag) {
        if(tag == null) return null;
        switch (tag) {
            case WeatherSyncJob.TAG:
                return new WeatherSyncJob();
            default:
                return null;
        }
    }
}
