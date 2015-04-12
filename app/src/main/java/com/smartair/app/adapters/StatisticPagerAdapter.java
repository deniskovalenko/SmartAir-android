package com.smartair.app.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.smartair.app.ui.fragments.Co2StatisticFragment;
import com.smartair.app.ui.fragments.HumidityStatisticFragment;
import com.smartair.app.ui.fragments.TemperatureStatisticFragment;

public class StatisticPagerAdapter extends FragmentPagerAdapter {
    String[] titles = {"Temperature", "CO2", "Humidity"};

    public StatisticPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TemperatureStatisticFragment.getInstance();
            case 1:
                return new Co2StatisticFragment();
            case 2:
                return new HumidityStatisticFragment();
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
        return titles[position];
    }
}
