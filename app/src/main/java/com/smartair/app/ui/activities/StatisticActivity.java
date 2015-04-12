package com.smartair.app.ui.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.smartair.app.R;
import com.smartair.app.adapters.StatisticPagerAdapter;
import com.smartair.app.components.database.DatabaseHelper;
import com.smartair.app.constants.IntentConstants;
import com.smartair.app.models.entities.Device;
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
        initActionBar();
        viewPager.setAdapter(new StatisticPagerAdapter(getSupportFragmentManager()));
        slidingTabLayout.setViewPager(viewPager);
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        SQLiteDatabase db = DatabaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = db.query(Device.Contract.TABLE_NAME,
                new String[]{Device.Contract.DEVICE_NAME},
                "_id=?", new String[]{getIntent().getStringExtra(IntentConstants.DEVICE_ID)},
                null, null, null);
        cursor.moveToFirst();
        getSupportActionBar().setTitle(cursor.getString(0));
        cursor.close();
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
