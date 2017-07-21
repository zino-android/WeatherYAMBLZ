package com.zino.mobilization.weatheryamblz.ui.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;
import com.zino.mobilization.weatheryamblz.presentation.presenter.WeatherPresenter;
import com.zino.mobilization.weatheryamblz.presentation.view.WeatherView;
import com.zino.mobilization.weatheryamblz.ui.service.UpdateWeatherService;
import com.zino.mobilization.weatheryamblz.utils.Utils;

import butterknife.BindView;


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

    @BindView(R.id.wind_text_view)
    TextView windSpeedTextView;

    private boolean isCelsius = true;

    private BroadcastReceiver br;

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
        onNavigationChanged.setTitle(getResources().getString(R.string.action_weather));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        br = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(UpdateWeatherService.WEATHER_LOADED_ACTION)) {
                    presenter.onWeatherLoadedFromService();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter(UpdateWeatherService.WEATHER_LOADED_ACTION);
        getActivity().registerReceiver(br, intentFilter);


    }

    @Override
    public void showWeather(WeatherResponse weatherResponse) {
        double temp = 0;
        if (isCelsius) {
            temp = weatherResponse.getMain().getTemp();
        } else {
            temp = Utils.celsiusToFahrenheit(weatherResponse.getMain().getTemp());
        }
        tempTextView.setText(Utils.formatTemperature(temp));

        double minTemp = 0;
        if (isCelsius) {
            minTemp = weatherResponse.getMain().getTempMin();
        } else {
            minTemp = Utils.celsiusToFahrenheit(weatherResponse.getMain().getTempMin());
        }
        minTempTextView.setText(Utils.formatTemperature(minTemp));

        double maxTemp = 0;
        if (isCelsius) {
            maxTemp = weatherResponse.getMain().getTempMax();
        } else {
            maxTemp = Utils.celsiusToFahrenheit(weatherResponse.getMain().getTempMax());
        }

        maxTempTextView.setText(Utils.formatTemperature(maxTemp));
        descriptionTextView.setText(weatherResponse.getWeather().get(0).getDescription());

        weatherImageView.setImageDrawable(
                getResources().getDrawable(
                        Utils.getImageIdByName(weatherResponse.getWeather().get(0).getIcon())));

        pressureTextView.setText(String.format(getResources().getString(R.string.pressure),
                String.valueOf(weatherResponse.getMain().getPressure())));

        humidityTextView.setText(String.format(getResources().getString(R.string.humidity),
                String.valueOf(weatherResponse.getMain().getHumidity())));

        visibilityTextView.setText(String.format(getResources().getString(R.string.visibility),
                String.valueOf(Utils.metersToKm(weatherResponse.getVisibility()))));

        windSpeedTextView.setText(String.format(getResources().getString(R.string.wind),
                String.valueOf(weatherResponse.getWind().getSpeed())));


    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setCelsius(boolean celsius) {
        isCelsius = celsius;
    }



    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(br);
    }
}
