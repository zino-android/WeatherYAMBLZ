package com.zino.mobilization.weatheryamblz.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.zino.mobilization.weatheryamblz.BuildConfig;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;
import com.zino.mobilization.weatheryamblz.presentation.presenter.WeatherPresenter;
import com.zino.mobilization.weatheryamblz.presentation.view.WeatherView;
import com.zino.mobilization.weatheryamblz.ui.view.WindView;
import com.zino.mobilization.weatheryamblz.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends BaseFragment implements WeatherView {

    @InjectPresenter
    WeatherPresenter presenter;

    @BindView(R.id.weather_image_view)
    ImageView weatherImageView;

    @BindView(R.id.temp_text_view)
    TextView tempTextView;

    @BindView(R.id.min_temp_text_view)
    TextView minTempTextView;

    @BindView(R.id.max_temp_text_view)
    TextView maxTempTextView;

    @BindView(R.id.description_text_view)
    TextView descriptionTextView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.pressure_text_view)
    TextView pressureTextView;

    @BindView(R.id.humidity_text_view)
    TextView humidityTextView;

    @BindView(R.id.visibility_text_view)
    TextView visibilityTextView;



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

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public void showWeather(WeatherResponse weatherResponse) {
        tempTextView.setText(Utils.formatTemperature(weatherResponse.getMain().getTemp()));
        minTempTextView.setText(Utils.formatTemperature(weatherResponse.getMain().getTempMin()));
        maxTempTextView.setText(Utils.formatTemperature(weatherResponse.getMain().getTempMax()));
        descriptionTextView.setText(weatherResponse.getWeather().get(0).getDescription());

        weatherImageView.setImageDrawable(
                getResources().getDrawable(
                        Utils.getImageIdByName(weatherResponse.getWeather().get(0).getIcon())));

        pressureTextView.setText(String.format(getResources().getString(R.string.pressure),
                String.valueOf(weatherResponse.getMain().getPressure())));

        humidityTextView.setText(String.format(getResources().getString(R.string.humidity),
                String.valueOf(weatherResponse.getMain().getHumidity())));

        visibilityTextView.setText(String.format(getResources().getString(R.string.humidity),
                String.valueOf(Utils.metersToKm(weatherResponse.getVisibility()))));

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        weatherImageView = null;
        tempTextView = null;
        minTempTextView = null;
        maxTempTextView = null;
        descriptionTextView = null;
        swipeRefreshLayout = null;
        pressureTextView = null;
        humidityTextView = null;
        visibilityTextView = null;
    }
}
