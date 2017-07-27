package com.zino.mobilization.weatheryamblz.di.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.zino.mobilization.weatheryamblz.BuildConfig;
import com.zino.mobilization.weatheryamblz.model.api.WeatherAPI;
import com.zino.mobilization.weatheryamblz.model.repository.WeatherRepository;
import com.zino.mobilization.weatheryamblz.model.repository.WeatherRepositoryImp;
import com.zino.mobilization.weatheryamblz.utils.DirsProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Denis Buzmakov on 27.07.17.
 * <buzmakov.da@gmail.com>
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if(BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(new StethoInterceptor());
        }
        return builder.build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client, GsonConverterFactory factory) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(WeatherAPI.BASE_URL)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    WeatherAPI provideWeatherApi(Retrofit retrofit) {
        return retrofit.create(WeatherAPI.class);
    }

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(DirsProvider dirsProvider, WeatherAPI api) {
        return new WeatherRepositoryImp(dirsProvider, api);
    }

}
