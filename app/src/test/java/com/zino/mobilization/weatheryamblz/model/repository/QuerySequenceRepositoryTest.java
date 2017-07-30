package com.zino.mobilization.weatheryamblz.model.repository;

import com.zino.mobilization.weatheryamblz.common.TestData;
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
    private final WeatherResponse apiResponse = TestData.getMoscowResponse();
    private final WeatherResponse cacheResponse = TestData.getPermResponse();

    @Mock
    private WeatherAPI api;

    @Mock
    private CacheManager cacheManager;

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

}
