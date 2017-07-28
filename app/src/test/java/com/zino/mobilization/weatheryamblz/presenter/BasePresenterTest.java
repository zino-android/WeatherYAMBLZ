package com.zino.mobilization.weatheryamblz.presenter;

import android.support.annotation.CallSuper;

import com.zino.mobilization.weatheryamblz.BuildConfig;
import com.zino.mobilization.weatheryamblz.TestApplication;
import com.zino.mobilization.weatheryamblz.model.prefs.SharedPreferencesHelper;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by Denis Buzmakov on 28.07.17.
 * <buzmakov.da@gmail.com>
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = TestApplication.class)
public abstract class BasePresenterTest {

    @Mock
    protected SharedPreferencesHelper preferencesHelper;

    @Before
    @CallSuper
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
}
