package com.smartair.app.ui.fragments;

import android.database.Cursor;
import android.os.Bundle;

import com.github.mikephil.charting.data.Entry;
import com.smartair.app.constants.LoaderConstants;
import com.smartair.app.models.entities.Indication;

public class Co2StatisticFragment extends TemperatureStatisticFragment {

    public static Co2StatisticFragment getInstance(String deviceId) {
        Bundle args = new Bundle();
        args.putString(DEVICE_ID_ARG, deviceId);
        Co2StatisticFragment fragment = new Co2StatisticFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String indicationColumn() {
        return Indication.Contract.CO2;
    }

    @Override
    protected Entry toEntry(Cursor cursor, int column, int position) {
        return new Entry(cursor.getInt(column), position);
    }

    @Override
    protected int getLoaderId() {
        return LoaderConstants.INDICATE_CO2_LOADER;
    }
}
