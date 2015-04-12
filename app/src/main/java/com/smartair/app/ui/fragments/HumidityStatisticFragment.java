package com.smartair.app.ui.fragments;

import com.github.mikephil.charting.data.Entry;
import com.smartair.app.models.entities.Indication;

import java.util.ArrayList;
import java.util.List;

public class HumidityStatisticFragment extends TemperatureStatisticFragment {

    @Override
    protected ArrayList<Entry> getEntries(List<Indication> indications) {
        ArrayList<Entry> entries = new ArrayList<>(indications.size());
        int index = 0;
        for (Indication indication : indications)
            entries.add(new Entry(indication.getHumidity(), index++));

        return entries;
    }
}
