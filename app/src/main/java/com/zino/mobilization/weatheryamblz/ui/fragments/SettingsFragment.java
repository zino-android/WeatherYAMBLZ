package com.zino.mobilization.weatheryamblz.ui.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.presentation.presenter.SettingsPresenter;
import com.zino.mobilization.weatheryamblz.presentation.view.SettingsView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SettingsFragment extends BaseFragment implements SettingsView {

    @InjectPresenter
    SettingsPresenter presenter;

    @BindView(R.id.fahrenheit_button)
    Button fahrenheitButton;
    @BindView(R.id.celsius_button)
    Button celsiusButton;

    @BindView(R.id.time_radio_group)
    RadioGroup timeRadioGroup;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        onNavigationChanged.setTitle(getResources().getString(R.string.action_settings));


        timeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                presenter.onTimeCheckedChanged(id);
            }
        });



    }

    @OnClick(R.id.celsius_button)
    void onCelsiusButtonClicked() {
        presenter.onCelsiusButtonClicked();
    }

    @OnClick(R.id.fahrenheit_button)
    void onFahrenheitButtonClicked() {
        presenter.onFahrenheitButtonClicked();
    }

    @Override
    public void setFahrenheitButtonActive() {
        celsiusButton.setEnabled(true);
        fahrenheitButton.setEnabled(false);
        fahrenheitButton.setTextColor(Color.WHITE);
        celsiusButton.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void setCelsiusButtonActive() {
        celsiusButton.setEnabled(false);
        fahrenheitButton.setEnabled(true);
        celsiusButton.setTextColor(Color.WHITE);
        fahrenheitButton.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void checkRadioButton(int id) {

        timeRadioGroup.check(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fahrenheitButton = null;
        celsiusButton = null;
        timeRadioGroup = null;
    }
}
