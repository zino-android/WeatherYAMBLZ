package com.zino.mobilization.weatheryamblz.ui.fragments;

import android.app.Activity;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.zino.mobilization.weatheryamblz.ui.activity.OnNavigationChanged;


/**
 * Created by Алексей on 14.07.2017.
 */

public class BaseFragment extends MvpAppCompatFragment {

    protected OnNavigationChanged onNavigationChanged;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onNavigationChanged = (OnNavigationChanged) activity;

    }

}
