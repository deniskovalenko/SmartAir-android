package com.smartair.app.adapters;

import android.content.Context;
import android.database.ContentObserver;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.smartair.app.R;
import com.smartair.app.ui.fragments.Co2StatisticFragment;
import com.smartair.app.ui.fragments.HumidityStatisticFragment;
import com.smartair.app.ui.fragments.TemperatureStatisticFragment;

public class StatisticPagerAdapter extends FragmentPagerAdapter {
    private int[] titles = { R.string.tab_temperature, R.string.tab_co2, R.string.tab_humidity };
    private final String deviceIs;
    private final Context context;



    public StatisticPagerAdapter(Context context, FragmentManager fm, String deviceId) {
        super(fm);
        this.deviceIs = deviceId;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TemperatureStatisticFragment.getInstance(deviceIs);
            case 1:
                return Co2StatisticFragment.getInstance(deviceIs);
            case 2:
                return HumidityStatisticFragment.getInstance(deviceIs);
            default:
                throw new IllegalArgumentException("Position not found");
        }
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return context.getString(titles[position]);
    }
}
