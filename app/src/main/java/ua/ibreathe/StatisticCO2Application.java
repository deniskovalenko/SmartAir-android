package ua.ibreathe;

import android.app.Application;

import com.octo.android.robospice.SpiceManager;

import ua.ibreathe.components.StatisticCO2SpiceService;

public class StatisticCO2Application  extends Application {
    private static StatisticCO2Application instance;
    private SpiceManager spiceManager;

    public static StatisticCO2Application getInstance() {
        return instance;
    }

    public SpiceManager getSpiceManager() {
        return spiceManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        spiceManager = new SpiceManager(StatisticCO2SpiceService.class);
        spiceManager.start(this);
    }
}
