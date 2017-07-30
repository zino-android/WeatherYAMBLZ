package com.zino.mobilization.weatheryamblz.ui.fragments;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.WeatherApplication;
import com.zino.mobilization.weatheryamblz.presentation.presenter.SettingsPresenter;
import com.zino.mobilization.weatheryamblz.presentation.view.SettingsView;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;


public class SettingsFragment extends BaseFragment implements SettingsView {
    private static final int REQUEST_CHOOSE_CITY = 777;

    @InjectPresenter
    SettingsPresenter presenter;

    @BindView(R.id.fahrenheit_button)
    Button fahrenheitButton;
    @BindView(R.id.celsius_button)
    Button celsiusButton;

    @BindView(R.id.time_radio_group)
    RadioGroup timeRadioGroup;

    @BindView(R.id.city_text_view)
    TextView cityTextView;

    @ProvidePresenter
    SettingsPresenter provideSettingsPresenter() {
        return WeatherApplication.getAppComponent().getSettingsPresenter();
    }

    @NonNull
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onNavigationChanged.setTitle(getResources().getString(R.string.action_settings));


        timeRadioGroup.setOnCheckedChangeListener((radioGroup, id) -> presenter.onTimeCheckedChanged(id));

    }

    @OnClick(R.id.view_choose_city)
    void onCityClicked() {
        presenter.onCityClicked();
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
    public void openChooseCity() {
        try {
            AutocompleteFilter filter = new AutocompleteFilter.Builder()
                    .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                    .build();
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .setFilter(filter)
                    .build(getActivity());
            startActivityForResult(intent, REQUEST_CHOOSE_CITY);
        } catch (GooglePlayServicesRepairableException e) {
            GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), e.getConnectionStatusCode(), 0);
        } catch (GooglePlayServicesNotAvailableException e) {
            String message = getString(R.string.error_play_services_not_available,
                    GoogleApiAvailability.getInstance().getErrorString(e.errorCode));
            Timber.e(message);
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setCurrentCityName(String name) {
        cityTextView.setText(name);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CHOOSE_CITY) {
            if(resultCode == Activity.RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);
                presenter.onCityChosen(place);
                Timber.d(place.toString());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                Timber.e(status.getStatusMessage());
                Toast.makeText(getActivity(), status.getStatusMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
