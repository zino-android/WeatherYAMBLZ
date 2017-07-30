package com.zino.mobilization.weatheryamblz.presenter.weather.base;

import com.zino.mobilization.weatheryamblz.model.pojo.City;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;
import com.zino.mobilization.weatheryamblz.model.repository.WeatherRepository;
import com.zino.mobilization.weatheryamblz.presentation.view.WeatherView$$State;
import com.zino.mobilization.weatheryamblz.presenter.BasePresenterTest;
import com.zino.mobilization.weatheryamblz.common.RxImmediateSchedulerRule;

import org.junit.ClassRule;
import org.mockito.Mock;

import io.reactivex.observers.TestObserver;
import io.reactivex.subjects.SingleSubject;

/**
 * Created by Denis Buzmakov on 27.07.17.
 * <buzmakov.da@gmail.com>
 */

public abstract class WeatherPresenterTest extends BasePresenterTest{
    protected TestPresenter presenter;

    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    @Mock
    protected WeatherView$$State viewState;

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
        presenter.setViewState(viewState);

        singleSubject.subscribe(responseObserver);
    }

}
