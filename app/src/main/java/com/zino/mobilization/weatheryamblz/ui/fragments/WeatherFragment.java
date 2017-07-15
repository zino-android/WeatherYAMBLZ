package com.zino.mobilization.weatheryamblz.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;
import com.zino.mobilization.weatheryamblz.presentation.presenter.WeatherPresenter;
import com.zino.mobilization.weatheryamblz.presentation.view.WeatherView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends BaseFragment implements WeatherView {

    @InjectPresenter
    WeatherPresenter presenter;

    @BindView(R.id.temp)
    TextView temp;



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

    @Override
    public void showWeather(WeatherResponse weatherResponse) {
        temp.setText(weatherResponse.getWeather().get(0).getDescription());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
