package com.zino.mobilization.weatheryamblz.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.zino.mobilization.weatheryamblz.BuildConfig;
import com.zino.mobilization.weatheryamblz.TestApplication;
import com.zino.mobilization.weatheryamblz.common.TestData;
import com.zino.mobilization.weatheryamblz.model.pojo.City;
import com.zino.mobilization.weatheryamblz.utils.AppResources;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import io.reactivex.observers.TestObserver;

import static com.zino.mobilization.weatheryamblz.model.prefs.SharedPreferencesHelperImpl.FILE_NAME;

/**
 * Created by Denis Buzmakov on 25.07.17.
 * <buzmakov.da@gmail.com>
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = TestApplication.class)
public class CurrentCityTest {

    private SharedPreferencesHelperImpl preferencesHelper;

    @Before
    public void init() {
        Context context = RuntimeEnvironment.application;
        AppResources resources = new AppResources(context);
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME,  Context.MODE_PRIVATE);
        RxSharedPreferences rxSharedPreferences = RxSharedPreferences.create(preferences);
        preferencesHelper = new SharedPreferencesHelperImpl(preferences, rxSharedPreferences, resources);
    }

    @Test
    public void shouldReturnDefaultCity() {
        TestObserver<City> observer = createTestObserverAndSubscribe();
        observer.assertValues(preferencesHelper.defaultCity());
        observer.dispose();
    }

    @Test
    public void shouldAddAndReturnNewYork() {
        TestObserver<City> observer = createTestObserverAndSubscribe();
        City newYork = TestData.getNewYork();
        preferencesHelper.setCurrentCity(newYork);
        observer.assertValues(preferencesHelper.defaultCity(), newYork);
        observer.dispose();
    }

    @Test
    public void shouldNotWriteNull() {
        TestObserver<City> observer = createTestObserverAndSubscribe();
        preferencesHelper.setCurrentCity(null);
        observer.assertValues(preferencesHelper.defaultCity());
        observer.dispose();
    }

    private TestObserver<City> createTestObserverAndSubscribe() {
        TestObserver<City> observer = new TestObserver<>();
        preferencesHelper.getCurrentCity()
                .subscribe(observer);
        return observer;
    }

}
