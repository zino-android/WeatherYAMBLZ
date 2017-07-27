package com.zino.mobilization.weatheryamblz.model.repository;

import com.google.gson.Gson;
import com.zino.mobilization.weatheryamblz.model.api.WeatherAPI;
import com.zino.mobilization.weatheryamblz.model.cache.CacheManager;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.observers.TestObserver;
import io.reactivex.subjects.SingleSubject;

import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by Denis Buzmakov on 27.07.17.
 * <buzmakov.da@gmail.com>
 */

public class QuerySequenceRepositoryTest {

    private SingleSubject<WeatherResponse> currentWeatherFromApi;
    private SingleSubject<WeatherResponse> currentWeatherFromCache;
    private WeatherResponse apiResponse = new Gson().fromJson(getMoscowWeatherJson(), WeatherResponse.class);
    private WeatherResponse cacheResponse = new Gson().fromJson(getPermWeatherJson(), WeatherResponse.class);

    @Mock
    WeatherAPI api;

    @Mock
    CacheManager cacheManager;

    private WeatherRepository repository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        currentWeatherFromApi = SingleSubject.create();
        currentWeatherFromCache = SingleSubject.create();
        when(api.getCurrentWeather(anyDouble(), anyDouble(), anyString(), anyString(), anyString()))
                .thenReturn(currentWeatherFromApi);
        when(cacheManager.getCurrentWeather())
                .thenReturn(currentWeatherFromCache);
        repository = new WeatherRepositoryImp(cacheManager, api);
    }

    @Test
    public void shouldReturnOnlyFromApi() {
        when(cacheManager.isCacheAvailable()).thenReturn(false);
        TestObserver<WeatherResponse> observer = new TestObserver<>();
        repository.getCurrentWeather(0, 0, "ru")
                .subscribe(observer);
        currentWeatherFromApi.onSuccess(apiResponse);
        observer.assertValues(apiResponse);
        observer.dispose();
    }

    @Test
    public void shouldReturnFromCacheAndApi() {
        when(cacheManager.isCacheAvailable()).thenReturn(true);
        TestObserver<WeatherResponse> observer = new TestObserver<>();
        repository.getCurrentWeather(0, 0, "ru")
                .subscribe(observer);
        currentWeatherFromCache.onSuccess(cacheResponse);
        currentWeatherFromApi.onSuccess(apiResponse);
        observer.assertValues(cacheResponse, apiResponse);
        observer.dispose();
    }

    private String getMoscowWeatherJson() {
        return "{\n" +
                "    \"coord\": {\n" +
                "        \"lon\": 37.62,\n" +
                "        \"lat\": 55.75\n" +
                "    },\n" +
                "    \"weather\": [\n" +
                "        {\n" +
                "            \"id\": 802,\n" +
                "            \"main\": \"Clouds\",\n" +
                "            \"description\": \"scattered clouds\",\n" +
                "            \"icon\": \"03d\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"base\": \"stations\",\n" +
                "    \"main\": {\n" +
                "        \"temp\": 300.91,\n" +
                "        \"pressure\": 1007,\n" +
                "        \"humidity\": 51,\n" +
                "        \"temp_min\": 300.15,\n" +
                "        \"temp_max\": 302.15\n" +
                "    },\n" +
                "    \"visibility\": 10000,\n" +
                "    \"wind\": {\n" +
                "        \"speed\": 6,\n" +
                "        \"deg\": 260\n" +
                "    },\n" +
                "    \"clouds\": {\n" +
                "        \"all\": 40\n" +
                "    },\n" +
                "    \"dt\": 1501158600,\n" +
                "    \"sys\": {\n" +
                "        \"type\": 1,\n" +
                "        \"id\": 7325,\n" +
                "        \"message\": 0.0044,\n" +
                "        \"country\": \"RU\",\n" +
                "        \"sunrise\": 1501118798,\n" +
                "        \"sunset\": 1501177452\n" +
                "    },\n" +
                "    \"id\": 524901,\n" +
                "    \"name\": \"Moscow\",\n" +
                "    \"cod\": 200\n" +
                "}";
    }

    private String getPermWeatherJson() {
        return "{\n" +
                "    \"coord\": {\n" +
                "        \"lon\": 56.29,\n" +
                "        \"lat\": 58.02\n" +
                "    },\n" +
                "    \"weather\": [\n" +
                "        {\n" +
                "            \"id\": 800,\n" +
                "            \"main\": \"Clear\",\n" +
                "            \"description\": \"clear sky\",\n" +
                "            \"icon\": \"02d\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"base\": \"stations\",\n" +
                "    \"main\": {\n" +
                "        \"temp\": 294.845,\n" +
                "        \"pressure\": 1001.94,\n" +
                "        \"humidity\": 78,\n" +
                "        \"temp_min\": 294.845,\n" +
                "        \"temp_max\": 294.845,\n" +
                "        \"sea_level\": 1022.12,\n" +
                "        \"grnd_level\": 1001.94\n" +
                "    },\n" +
                "    \"wind\": {\n" +
                "        \"speed\": 2.47,\n" +
                "        \"deg\": 320.504\n" +
                "    },\n" +
                "    \"clouds\": {\n" +
                "        \"all\": 8\n" +
                "    },\n" +
                "    \"dt\": 1501162005,\n" +
                "    \"sys\": {\n" +
                "        \"message\": 0.0051,\n" +
                "        \"country\": \"RU\",\n" +
                "        \"sunrise\": 1501113521,\n" +
                "        \"sunset\": 1501173756\n" +
                "    },\n" +
                "    \"id\": 511196,\n" +
                "    \"name\": \"Perm\",\n" +
                "    \"cod\": 200\n" +
                "}";
    }

}
