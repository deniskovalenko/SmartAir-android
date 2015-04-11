package com.smartair.app;

import android.app.Application;

import com.octo.android.robospice.SpiceManager;

import com.smartair.app.components.SmartAirSpiceService;

public class SmartAirApplication extends Application {
    private static SmartAirApplication instance;
    private SpiceManager spiceManager;

    public static SmartAirApplication getInstance() {
        return instance;
    }

    public SpiceManager getSpiceManager() {
        return spiceManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        spiceManager = new SpiceManager(SmartAirSpiceService.class);
        spiceManager.start(this);
    }
}
