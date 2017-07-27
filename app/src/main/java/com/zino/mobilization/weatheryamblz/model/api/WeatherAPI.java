package com.zino.mobilization.weatheryamblz.model.api;

import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface WeatherAPI {

    String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    @GET("weather")
    Observable<WeatherResponse> getCurrentWeather(@Query("lat") double lat,
                                                  @Query("lon") double lon,
                                                  @Query("appid") String apiKey,
                                                  @Query("lang") String lang,
                                                  @Query("units") String units);

}
