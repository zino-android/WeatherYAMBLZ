package com.zino.mobilization.weatheryamblz.presenter.settings.base;

import com.zino.mobilization.weatheryamblz.presentation.view.SettingsView;
import com.zino.mobilization.weatheryamblz.presenter.BasePresenterTest;
import com.zino.mobilization.weatheryamblz.utils.AndroidJobHelper;

import org.junit.After;
import org.mockito.Mock;

/**
 * Created by Denis Buzmakov on 27.07.17.
 * <buzmakov.da@gmail.com>
 */

public abstract class SettingsPresenterTest extends BasePresenterTest{
    protected TestPresenter presenter;

    @Mock
    protected SettingsView view;

    @Mock
    protected AndroidJobHelper jobHelper;

    @Override
    public void init() {
        super.init();
        presenter = new TestPresenter();
        presenter.attachView(view);
    }

    @After
    public void after() {
        presenter.destroyView(view);
    }
}
