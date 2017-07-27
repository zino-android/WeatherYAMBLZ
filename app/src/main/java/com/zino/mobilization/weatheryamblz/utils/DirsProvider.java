package com.zino.mobilization.weatheryamblz.utils;

import android.content.Context;

import java.io.File;

/**
 * Created by Denis Buzmakov on 27.07.17.
 * <buzmakov.da@gmail.com>
 */

public class DirsProvider {

    private Context context;

    public DirsProvider(Context context) {
        this.context = context;
    }

    public File getFilesDir() {
        return context.getFilesDir();
    }
}
