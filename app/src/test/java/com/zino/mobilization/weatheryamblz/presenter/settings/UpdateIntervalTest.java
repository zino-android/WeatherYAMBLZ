package com.zino.mobilization.weatheryamblz.presenter.settings;

import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.presenter.settings.base.SettingsPresenterTest;

import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by Denis Buzmakov on 28.07.17.
 * <buzmakov.da@gmail.com>
 */

public class UpdateIntervalTest extends SettingsPresenterTest {

    @Test
    public void shouldChangeIntervalAndReschedule() {
        int radioId = R.id.radio_fifteen;
        long period = 900_000L;
        presenter.onTimeCheckedChanged(radioId);
        verify(preferencesHelper, atLeastOnce()).setUpdateTime(period);
        verify(preferencesHelper, atLeastOnce()).setTimeRadioButtonId(radioId);
        verify(jobHelper, atLeastOnce()).changeSchedulePeriod(period);
    }

    @Test
    public void shouldChangeIntervalAndCancel() {
        int radioId = R.id.radio_manually;
        long period = 0;
        presenter.onTimeCheckedChanged(radioId);
        verify(preferencesHelper, atLeastOnce()).setUpdateTime(period);
        verify(preferencesHelper, atLeastOnce()).setTimeRadioButtonId(radioId);
        verify(jobHelper, atLeastOnce()).cancelAllJobs();
    }

    @Test
    public void shouldNotChangeInterval() {
        presenter.onTimeCheckedChanged(0);
        verify(preferencesHelper, never()).setUpdateTime(anyLong());
        verify(preferencesHelper, never()).setTimeRadioButtonId(anyInt());
        verify(jobHelper, never()).cancelAllJobs();
        verify(jobHelper, never()).changeSchedulePeriod(anyLong());
    }

}
