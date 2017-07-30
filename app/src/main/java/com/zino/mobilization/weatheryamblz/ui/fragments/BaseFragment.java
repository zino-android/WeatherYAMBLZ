package com.zino.mobilization.weatheryamblz.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.zino.mobilization.weatheryamblz.ui.activity.OnNavigationChanged;

import butterknife.ButterKnife;
import butterknife.Unbinder;



abstract public class BaseFragment extends MvpAppCompatFragment {

    protected OnNavigationChanged onNavigationChanged;
    private Unbinder unbinder;

    @NonNull
    protected abstract View createView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnNavigationChanged) {
            onNavigationChanged = (OnNavigationChanged) context;
        } else {
            throw new ClassCastException(getClass().getName() + " must implement OnNavigationChanged");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = createView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
