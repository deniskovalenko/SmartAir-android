package com.smartair.app.ui.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.smartair.app.R;
import com.smartair.app.SmartAirApplication;
import com.smartair.app.adapters.StatisticPagerAdapter;
import com.smartair.app.components.SimpleRetrofitCallback;
import com.smartair.app.components.database.DatabaseHelper;
import com.smartair.app.components.provider.SmartAirProvider;
import com.smartair.app.constants.IntentConstants;
import com.smartair.app.models.entities.Device;
import com.smartair.app.models.entities.Indication;
import com.smartair.app.models.requests.GetStatisticRequest;
import com.smartair.app.models.responses.GetStatisticResponse;
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
        requestOnServer();
        initActionBar();
        viewPager.setAdapter(new StatisticPagerAdapter(getSupportFragmentManager()));
        slidingTabLayout.setViewPager(viewPager);
    }

    protected void requestOnServer() {
        final String deviceId = getIntent().getStringExtra(IntentConstants.DEVICE_ID);
        SmartAirApplication.getInstance().getSpiceManager().execute(new GetStatisticRequest(deviceId), new SimpleRetrofitCallback<GetStatisticResponse>() {

            @Override
            public void onRequestSuccess(GetStatisticResponse indications) {
                Log.d("TEST_TAG", "onRequestSuccess size = " + indications.size());
                SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();
                db.delete(Indication.Contract.TABLE_NAME, Indication.Contract.DEVICE_ID + "=?", new String[] {deviceId});
                for (Indication indication : indications)
                    db.insert(Indication.Contract.TABLE_NAME, null, indication.toCV());
                Log.d("TEST_TAG", "notifyChange");
                getContentResolver().notifyChange(SmartAirProvider.INDICATION_CONTENT_URI, null);
            }
        });
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
