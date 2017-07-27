package com.zino.mobilization.weatheryamblz.model.repository;

import android.util.Log;

import com.google.gson.Gson;
import com.zino.mobilization.weatheryamblz.model.api.WeatherAPI;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;
import com.zino.mobilization.weatheryamblz.utils.DirsProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;



public class WeatherRepositoryImp implements WeatherRepository {

    private static final String CURRENT_WEATHER_FILE_NAME = "current_weather.json";

    private DirsProvider dirsProvider;
    private WeatherAPI api;

    public WeatherRepositoryImp(DirsProvider dirsProvider, WeatherAPI api) {
        this.dirsProvider = dirsProvider;
        this.api = api;
    }

    @Override
    public Observable<WeatherResponse> getCurrentWeather(double lat, double lon, String lang) {
        Observable<WeatherResponse> fromApi = api
                .getCurrentWeather(lat, lon, "ad0dae19ea9cd24058581481b3ce84ce", lang, "metric")
                .doOnNext(this::saveCurrentWeather)
                .subscribeOn(Schedulers.io());
        if(checkIfCacheAvailable()) {
            Observable<WeatherResponse> fromCache = Observable.fromCallable(this::restoreCurrentWeather);
            return Observable.concat(fromCache, fromApi);
        } else return fromApi;
    }

    private void saveCurrentWeather(WeatherResponse weatherResponse) {
        File file = new File(dirsProvider.getFilesDir(), CURRENT_WEATHER_FILE_NAME);
        try(FileWriter writer = new FileWriter(file, false))
        {
            Gson gson = new Gson();
            String text = gson.toJson(weatherResponse);
            writer.write(text);
            writer.flush();
        }

        catch(IOException ex){
            Log.e(getClass().getName(), "saveCurrentWeather: ", ex);
        }
    }

    private WeatherResponse restoreCurrentWeather() {
        File file = new File(dirsProvider.getFilesDir(), CURRENT_WEATHER_FILE_NAME);

        BufferedReader br = null;
        FileReader fr = null;
        StringBuilder sb = new StringBuilder();

        try {

            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String currentLine;



            while ((currentLine = br.readLine()) != null) {
                sb.append(currentLine);
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

        return new Gson().fromJson(sb.toString(), WeatherResponse.class);
    }


    private boolean checkIfCacheAvailable() {
        File file = new File(dirsProvider.getFilesDir(), CURRENT_WEATHER_FILE_NAME);
        return file.exists();
    }

    @Override
    public Observable<WeatherResponse> getCurrentWeatherFromCache() {
        if (checkIfCacheAvailable()) {
            return Observable.fromCallable(this::restoreCurrentWeather);
        } else {
            return Observable.empty();
        }
    }
}
