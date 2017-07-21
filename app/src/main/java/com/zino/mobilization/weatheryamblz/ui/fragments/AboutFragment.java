package com.zino.mobilization.weatheryamblz.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zino.mobilization.weatheryamblz.BuildConfig;
import com.zino.mobilization.weatheryamblz.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutFragment extends BaseFragment {

    @BindView(R.id.version_text_view)
    TextView versionTextView;


    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        onNavigationChanged.setTitle(getResources().getString(R.string.action_about));
        setVersionName();
    }

    private void setVersionName() {
        String version = BuildConfig.VERSION_NAME;
        versionTextView.setText(String.format(getResources().getString(R.string.version), version));
    }


}
