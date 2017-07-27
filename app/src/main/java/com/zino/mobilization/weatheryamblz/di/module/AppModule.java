package com.zino.mobilization.weatheryamblz.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.zino.mobilization.weatheryamblz.model.SharedPreferencesHelper;
import com.zino.mobilization.weatheryamblz.ui.AppResources;
import com.zino.mobilization.weatheryamblz.utils.AndroidJobHelper;
import com.zino.mobilization.weatheryamblz.utils.DirsProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.zino.mobilization.weatheryamblz.model.SharedPreferencesHelper.FILE_NAME;

/**
 * Created by Denis Buzmakov on 27.07.17.
 * <buzmakov.da@gmail.com>
 */

@Module
public class AppModule {
    private Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs(Context context) {
        return context.getSharedPreferences(FILE_NAME,  Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    RxSharedPreferences provideRxSharedPreferences(SharedPreferences preferences) {
        return RxSharedPreferences.create(preferences);
    }

    @Provides
    @Singleton
    AppResources provideResources(Context context) {
        return new AppResources(context);
    }

    @Provides
    @Singleton
    SharedPreferencesHelper providePrefsHelper(SharedPreferences preferences,
                                               RxSharedPreferences rxSharedPreferences,
                                               AppResources appResources) {
        return new SharedPreferencesHelper(preferences, rxSharedPreferences, appResources);
    }

    @Provides
    @Singleton
    DirsProvider provideDirsProvider(Context context) {
        return new DirsProvider(context);
    }

    @Provides
    @Singleton
    AndroidJobHelper provideJobHelper(Context context) {
        return new AndroidJobHelper(context);
    }

}
