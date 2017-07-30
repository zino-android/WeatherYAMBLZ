package com.zino.mobilization.weatheryamblz.presenter.settings.base;

import com.zino.mobilization.weatheryamblz.presentation.view.SettingsView$$State;
import com.zino.mobilization.weatheryamblz.presenter.BasePresenterTest;
import com.zino.mobilization.weatheryamblz.utils.AndroidJobHelper;

import org.mockito.Mock;

import io.reactivex.subjects.PublishSubject;

import static org.mockito.Mockito.when;

/**
 * Created by Denis Buzmakov on 27.07.17.
 * <buzmakov.da@gmail.com>
 */

public abstract class SettingsPresenterTest extends BasePresenterTest{
    protected TestPresenter presenter;

    @Mock
    protected AndroidJobHelper jobHelper;

    @Mock
    protected SettingsView$$State viewState;

    @Override
    public void init() {
        super.init();
        presenter = new TestPresenter(preferencesHelper, jobHelper);
        when(preferencesHelper.getCurrentCity()).thenReturn(PublishSubject.create());
        presenter.setViewState(viewState);
    }
}
