package com.tigerspike;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.tigerspike.network.VolleyManager;

public class TigerSpikeApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        initializeVolley();
    }

    private void initializeVolley() {

        int memoryClass = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        int cacheSize = memoryClass * 1024 / 4;

        //Initialize volley framework
        VolleyManager.initialize(this, cacheSize);
    }

}
