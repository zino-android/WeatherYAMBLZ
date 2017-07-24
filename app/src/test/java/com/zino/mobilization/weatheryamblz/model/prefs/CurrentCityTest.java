package com.zino.mobilization.weatheryamblz.model.prefs;

import android.content.Context;

import com.zino.mobilization.weatheryamblz.BuildConfig;
import com.zino.mobilization.weatheryamblz.TestApplication;
import com.zino.mobilization.weatheryamblz.model.SharedPreferencesHelper;
import com.zino.mobilization.weatheryamblz.model.pojo.City;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import io.reactivex.observers.TestObserver;

/**
 * Created by Denis Buzmakov on 25.07.17.
 * <buzmakov.da@gmail.com>
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = TestApplication.class)
public class CurrentCityTest {

    private Context context;

    @Before
    public void init() {
        context = RuntimeEnvironment.application;
    }

    @Test
    public void shouldReturnDefaultCity() {
        TestObserver<City> observer = createTestObserverAndSubscribe();
        observer.assertValues(SharedPreferencesHelper.defaultCity(context));
    }

    @Test
    public void shouldAddAndReturnNewYork() {
        TestObserver<City> observer = createTestObserverAndSubscribe();
        SharedPreferencesHelper.setCurrentCity(context, createNewYork());
        observer.assertValues(SharedPreferencesHelper.defaultCity(context), createNewYork());
    }

    @Test
    public void shouldNotWriteNull() {
        TestObserver<City> observer = createTestObserverAndSubscribe();
        SharedPreferencesHelper.setCurrentCity(context, null);
        observer.assertValues(SharedPreferencesHelper.defaultCity(context));
    }

    private City createNewYork() {
        return new City("New York", 40.730610, -73.935242);
    }

    private TestObserver<City> createTestObserverAndSubscribe() {
        TestObserver<City> observer = new TestObserver<>();
        SharedPreferencesHelper.getCurrentCity(context)
                .subscribe(observer);
        return observer;
    }

}
