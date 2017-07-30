package com.zino.mobilization.weatheryamblz.utils;

import android.content.Context;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;
import com.zino.mobilization.weatheryamblz.WeatherJobCreator;
import com.zino.mobilization.weatheryamblz.WeatherSyncJob;

import java.util.Set;



public class AndroidJobHelper {

    private Context context;

    public AndroidJobHelper(Context context) {
        this.context = context;
    }

    public void scheduleIfJobRequestsIsEmpty(long period) {
        JobManager.create(context).addJobCreator(new WeatherJobCreator());
        Set<JobRequest> jobRequests = JobManager.instance().getAllJobRequestsForTag(WeatherSyncJob.TAG);
        if (jobRequests.isEmpty()) {
            WeatherSyncJob.scheduleJob(period);
        }
    }

    public void changeSchedulePeriod(long period) {
        JobManager.create(context).addJobCreator(new WeatherJobCreator());
        JobManager.instance().cancelAllForTag(WeatherSyncJob.TAG);
        WeatherSyncJob.scheduleJob(period);
    }

    public void cancelAllJobs() {
        JobManager.create(context).addJobCreator(new WeatherJobCreator());
        JobManager.instance().cancelAllForTag(WeatherSyncJob.TAG);
    }

}
