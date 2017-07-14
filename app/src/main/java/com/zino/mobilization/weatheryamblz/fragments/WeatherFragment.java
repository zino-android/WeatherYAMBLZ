package com.zino.mobilization.weatheryamblz.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zino.mobilization.weatheryamblz.BaseFragment;
import com.zino.mobilization.weatheryamblz.OnNavigationChanged;
import com.zino.mobilization.weatheryamblz.R;

import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends BaseFragment {




    public WeatherFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        onNavigationChanged.setTitle(getResources().getString(R.string.action_weather));
        onNavigationChanged.setMainScreen(true);

    }
}
