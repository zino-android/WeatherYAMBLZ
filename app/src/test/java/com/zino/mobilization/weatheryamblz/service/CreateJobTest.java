package com.zino.mobilization.weatheryamblz.service;

import com.evernote.android.job.Job;
import com.zino.mobilization.weatheryamblz.WeatherJobCreator;
import com.zino.mobilization.weatheryamblz.WeatherSyncJob;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
/**
 * Created by Denis Buzmakov on 21.07.2017.
 * <buzmakov.da@gmail.com>
 */

public class CreateJobTest {

    private Job createJob(String tag) {
        return new WeatherJobCreator().create(tag);
    }

    @Test
    public void shouldReturnWeatherJob() {
        assertThat(createJob(WeatherSyncJob.TAG), instanceOf(WeatherSyncJob.class));
    }

    @Test
    public void shouldReturnNull() {
        assertThat(createJob(null), nullValue());
        assertThat(createJob("azaza"), nullValue());
    }
}
