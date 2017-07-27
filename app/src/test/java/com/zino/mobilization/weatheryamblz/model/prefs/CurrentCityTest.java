package com.zino.mobilization.weatheryamblz.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.zino.mobilization.weatheryamblz.BuildConfig;
import com.zino.mobilization.weatheryamblz.TestApplication;
import com.zino.mobilization.weatheryamblz.model.SharedPreferencesHelper;
import com.zino.mobilization.weatheryamblz.model.pojo.City;
import com.zino.mobilization.weatheryamblz.ui.AppResources;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import io.reactivex.observers.TestObserver;

import static com.zino.mobilization.weatheryamblz.model.SharedPreferencesHelper.FILE_NAME;

/**
 * Created by Denis Buzmakov on 25.07.17.
 * <buzmakov.da@gmail.com>
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = TestApplication.class)
public class CurrentCityTest {

    private SharedPreferencesHelper preferencesHelper;

    @Before
    public void init() {
        Context context = RuntimeEnvironment.application;
        AppResources resources = new AppResources(context);
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME,  Context.MODE_PRIVATE);
        RxSharedPreferences rxSharedPreferences = RxSharedPreferences.create(preferences);
        preferencesHelper = new SharedPreferencesHelper(preferences, rxSharedPreferences, resources);
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
        preferencesHelper.setCurrentCity(createNewYork());
        observer.assertValues(preferencesHelper.defaultCity(), createNewYork());
        observer.dispose();
    }

    @Test
    public void shouldNotWriteNull() {
        TestObserver<City> observer = createTestObserverAndSubscribe();
        preferencesHelper.setCurrentCity(null);
        observer.assertValues(preferencesHelper.defaultCity());
        observer.dispose();
    }

    private City createNewYork() {
        return new City("New York", 40.730610, -73.935242);
    }

    private TestObserver<City> createTestObserverAndSubscribe() {
        TestObserver<City> observer = new TestObserver<>();
        preferencesHelper.getCurrentCity()
                .subscribe(observer);
        return observer;
    }

}
