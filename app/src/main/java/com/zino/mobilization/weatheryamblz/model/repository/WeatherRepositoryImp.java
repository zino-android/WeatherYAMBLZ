package com.zino.mobilization.weatheryamblz.model.repository;

import android.util.Log;

import com.google.gson.Gson;
import com.zino.mobilization.weatheryamblz.WeatherApplication;
import com.zino.mobilization.weatheryamblz.model.api.ApiInstance;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;



public class WeatherRepositoryImp implements WeatherRepository {

    private static final String CURRENT_WEATHER_FILE_NAME = "current_weather.json";

    @Override
    public void getCurrentWeather(long cityId, String lang, OnCurrentWeatherLoadedListener listener) {
        if (checkIfCacheAvailable()) {
            listener.onCurrentWeatherLoaded(restoreCurrentWeather());
        }

         ApiInstance.getAPI()
            .getCurrentWeather(cityId, "ad0dae19ea9cd24058581481b3ce84ce", lang, "metric")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(weatherResponse -> {weatherLoaded(weatherResponse, listener);},
                    throwable -> {onError(throwable, listener);});
    }



    private void weatherLoaded(WeatherResponse weatherResponse, OnCurrentWeatherLoadedListener listener) {
        listener.onCurrentWeatherLoaded(weatherResponse);
        saveCurrentWeather(weatherResponse);
    }

    private void onError(Throwable throwable, OnCurrentWeatherLoadedListener listener) {
        throwable.printStackTrace();
        listener.onError();
    }

    private void saveCurrentWeather(WeatherResponse weatherResponse) {
        File file = new File(WeatherApplication.context.getFilesDir(), CURRENT_WEATHER_FILE_NAME);
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
        File file = new File(WeatherApplication.context.getFilesDir(), CURRENT_WEATHER_FILE_NAME);

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
        File file = new File(WeatherApplication.context.getFilesDir(), CURRENT_WEATHER_FILE_NAME);
        return file.exists();
    }

}
