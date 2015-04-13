package com.smartair.app.ui.fragments;

import com.smartair.app.constants.LoaderConstants;
import com.smartair.app.models.entities.Indication;

public class HumidityStatisticFragment extends TemperatureStatisticFragment {

    @Override
    protected String indicationColumn() {
        return Indication.Contract.HUMIDITY;
    }

    @Override
    protected int getLoaderId() {
        return LoaderConstants.INDICATE_HUMIDITY_LOADER;
    }
}
