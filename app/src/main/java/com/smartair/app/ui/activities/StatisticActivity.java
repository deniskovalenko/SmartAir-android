package com.smartair.app.ui.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.smartair.app.R;
import com.smartair.app.adapters.StatisticPagerAdapter;
import com.smartair.app.ui.widgets.SlidingTabLayout;

public class StatisticActivity extends BaseActivity {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.viewPager)
    ViewPager viewPager;

    @InjectView(R.id.slidingTabLayout)
    SlidingTabLayout slidingTabLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_statistic;
    }

    @Override
    protected void onViewCreated() {
        setSupportActionBar(toolbar);
        viewPager.setAdapter(new StatisticPagerAdapter(getSupportFragmentManager()));
        slidingTabLayout.setViewPager(viewPager);
    }

    @Override
    protected int getFragmentContainerId() {
        return 0;
    }

    @Override
    protected void findView() {
        ButterKnife.inject(this);
    }
}
