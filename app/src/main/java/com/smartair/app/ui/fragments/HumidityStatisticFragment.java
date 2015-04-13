package com.smartair.app.ui.fragments;

import android.os.Bundle;

import com.smartair.app.constants.LoaderConstants;
import com.smartair.app.models.entities.Indication;

public class HumidityStatisticFragment extends TemperatureStatisticFragment {

    public static HumidityStatisticFragment getInstance(String deviceId) {
        Bundle args = new Bundle();
        args.putString(DEVICE_ID_ARG, deviceId);
        HumidityStatisticFragment fragment = new HumidityStatisticFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected String indicationColumn() {
        return Indication.Contract.HUMIDITY;
    }

    @Override
    protected int getLoaderId() {
        return LoaderConstants.INDICATE_HUMIDITY_LOADER;
    }
}
