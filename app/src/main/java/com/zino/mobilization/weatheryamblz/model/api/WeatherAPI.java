package com.zino.mobilization.weatheryamblz.model.api;

import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface WeatherAPI {


    @GET("weather")
    Observable<WeatherResponse> getCurrentWeather(@Query("id") long cityId,
                                                  @Query("appid") String apiKey,
                                                  @Query("lang") String lang,
                                                  @Query("units") String units);

}
