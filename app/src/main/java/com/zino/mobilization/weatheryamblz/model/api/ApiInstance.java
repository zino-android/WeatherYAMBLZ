package com.zino.mobilization.weatheryamblz.model.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.zino.mobilization.weatheryamblz.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiInstance {

    private static WeatherAPI api;


    public static WeatherAPI getAPI() {
        if (api == null) {

            OkHttpClient client;
            if (BuildConfig.DEBUG) {
                client = new OkHttpClient.Builder()
                        .addNetworkInterceptor(new StethoInterceptor())
                        .build();
            } else {
                client = new OkHttpClient();
            }


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();

            api = retrofit.create(WeatherAPI.class);
        }

        return api;
    }


}
