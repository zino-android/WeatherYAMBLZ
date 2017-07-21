package com.zino.mobilization.weatheryamblz.utils;

import android.content.Context;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;
import com.zino.mobilization.weatheryamblz.WeatherJobCreator;
import com.zino.mobilization.weatheryamblz.WeatherSyncJob;

import java.util.Set;



public class AndroidJobHelper {

    public static void scheduleIfJobRequestsIsEmpty(Context context, long period) {
        JobManager.create(context).addJobCreator(new WeatherJobCreator());
        Set<JobRequest> jobRequests = JobManager.instance().getAllJobRequestsForTag(WeatherSyncJob.TAG);
        if (jobRequests.isEmpty()) {
            WeatherSyncJob.scheduleJob(period);
        }
    }

    public static void changeSchedulePeriod(Context context, long period) {
        JobManager.create(context).addJobCreator(new WeatherJobCreator());
        JobManager.instance().cancelAllForTag(WeatherSyncJob.TAG);
        WeatherSyncJob.scheduleJob(period);
    }

    public static void cancelAllJobs(Context context) {
        JobManager.create(context).addJobCreator(new WeatherJobCreator());
        JobManager.instance().cancelAllForTag(WeatherSyncJob.TAG);
    }

}
