package com.zino.mobilization.weatheryamblz.presenter.weather.base;

import com.zino.mobilization.weatheryamblz.model.pojo.City;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;
import com.zino.mobilization.weatheryamblz.model.repository.WeatherRepository;
import com.zino.mobilization.weatheryamblz.presentation.view.WeatherView;
import com.zino.mobilization.weatheryamblz.presenter.BasePresenterTest;

import org.mockito.Mock;

import io.reactivex.observers.TestObserver;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.SingleSubject;

import static org.mockito.Mockito.when;

/**
 * Created by Denis Buzmakov on 27.07.17.
 * <buzmakov.da@gmail.com>
 */

public abstract class WeatherPresenterTest extends BasePresenterTest{
    protected TestPresenter presenter;

    @Mock
    protected WeatherView view;

    @Mock
    protected WeatherRepository weatherRepository;

    @Mock
    protected City currentCity;

    @Mock
    protected WeatherResponse weatherResponse;

    protected final SingleSubject<WeatherResponse> singleSubject = SingleSubject.create();
    protected final TestObserver<WeatherResponse> responseObserver = new TestObserver<>();

    @Override
    public void init() {
        super.init();
        presenter = new TestPresenter(preferencesHelper, weatherRepository);
        when(preferencesHelper.getCurrentCity()).thenReturn(PublishSubject.create());

        singleSubject.subscribe(responseObserver);
    }

}
