package com.zino.mobilization.weatheryamblz.presenter;

import android.support.annotation.CallSuper;

import com.zino.mobilization.weatheryamblz.model.prefs.SharedPreferencesHelper;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Created by Denis Buzmakov on 28.07.17.
 * <buzmakov.da@gmail.com>
 */

public abstract class BasePresenterTest {

    @Mock
    protected SharedPreferencesHelper preferencesHelper;

    @Before
    @CallSuper
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
}
