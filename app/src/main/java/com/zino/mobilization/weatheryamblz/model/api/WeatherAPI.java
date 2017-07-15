package com.zino.mobilization.weatheryamblz.model.api;

import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface WeatherAPI {

    //http://api.openweathermap.org/data/2.5/weather?id=524901&appid=ad0dae19ea9cd24058581481b3ce84ce&lang=ru&units=metric

    @GET("weather")
    Observable<WeatherResponse> getCurrentWeather(@Query("id") long cityId,
                                                  @Query("appid") String apiKey,
                                                  @Query("lang") String lang,
                                                  @Query("units") String units);

}
