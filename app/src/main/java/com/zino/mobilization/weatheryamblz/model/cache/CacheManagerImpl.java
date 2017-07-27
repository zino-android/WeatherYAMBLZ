package com.zino.mobilization.weatheryamblz.model.cache;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import io.reactivex.Single;

/**
 * Created by Denis Buzmakov on 27.07.17.
 * <buzmakov.da@gmail.com>
 */

public class CacheManagerImpl implements CacheManager {
    private static final String CURRENT_WEATHER_FILE_NAME = "current_weather.json";

    private Context context;

    public CacheManagerImpl(Context context) {
        this.context = context;
    }

    @Override
    public void saveCurrentWeather(WeatherResponse weatherResponse) {
        File file = new File(context.getFilesDir(), CURRENT_WEATHER_FILE_NAME);
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

    @Override
    public Single<WeatherResponse> getCurrentWeather() {
        File file = new File(context.getFilesDir(), CURRENT_WEATHER_FILE_NAME);

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

        return Single.just(new Gson().fromJson(sb.toString(), WeatherResponse.class));
    }

    @Override
    public boolean isCacheAvailable() {
        File file = new File(context.getFilesDir(), CURRENT_WEATHER_FILE_NAME);
        return file.exists();
    }
}
