package com.zino.mobilization.weatheryamblz;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by Алексей on 14.07.2017.
 */

public class BaseFragment extends Fragment {

    protected OnNavigationChanged onNavigationChanged;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onNavigationChanged = (OnNavigationChanged) activity;

    }

}
