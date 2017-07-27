package com.zino.mobilization.weatheryamblz.utils;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Created by Denis Buzmakov on 27.07.17.
 * <buzmakov.da@gmail.com>
 */

public class AppResources {

    private Context context;

    public AppResources(Context context) {
        this.context = context;
    }

    public String getString(@StringRes int res) {
        return context.getString(res);
    }

}
