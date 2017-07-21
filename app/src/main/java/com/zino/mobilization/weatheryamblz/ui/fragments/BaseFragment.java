package com.zino.mobilization.weatheryamblz.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.zino.mobilization.weatheryamblz.ui.activity.OnNavigationChanged;

import butterknife.ButterKnife;
import butterknife.Unbinder;




public class BaseFragment extends MvpAppCompatFragment {

    protected OnNavigationChanged onNavigationChanged;
    protected Unbinder unbinder;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onNavigationChanged = (OnNavigationChanged) activity;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
