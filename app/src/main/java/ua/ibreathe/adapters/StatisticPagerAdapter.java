package ua.ibreathe.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ua.ibreathe.ui.fragments.ChartStatisticFragment;

public class StatisticPagerAdapter extends FragmentPagerAdapter {
    String[] titles = {"Temperature", "CO2", "etc"};

    public StatisticPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ChartStatisticFragment.getInstance();
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
