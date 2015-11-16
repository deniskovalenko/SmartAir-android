package com.smartair.app.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.octo.android.robospice.persistence.exception.SpiceException;

import butterknife.InjectView;
import com.smartair.app.R;
import com.smartair.app.SmartAirApplication;
import com.smartair.app.adapters.DeviceCursorAdapter;
import com.smartair.app.components.SimpleRetrofitCallback;
import com.smartair.app.components.database.DatabaseHelper;
import com.smartair.app.components.provider.SmartAirProvider;
import com.smartair.app.constants.IntentConstants;
import com.smartair.app.constants.LoaderConstants;
import com.smartair.app.models.entities.Device;
import com.smartair.app.models.requests.GetDevicesRequest;
import com.smartair.app.models.responses.GetDevicesResponse;
import com.smartair.app.ui.activities.StatisticActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DevicesListFragment extends BaseRefreshFragment implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    @InjectView(R.id.list)
    ListView list;

    private final String UPDATE_KEY = "update_key";
    protected final String DEFAULT_USER = "f03ad31e-d3cc-4635-9809-7185864f6398";
    private SharedPreferences preferences;

    public static DevicesListFragment getInstance() {
        return new DevicesListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getActivity().getSharedPreferences("smartAir", Context.MODE_PRIVATE);
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initView();
        initializeLoader();
        requestOnServer();
    }

    protected void initView() {
        updateLastDate(preferences.getLong(UPDATE_KEY, -1));
        list.setAdapter(new DeviceCursorAdapter(getActivity(), null));
    }

    @SuppressLint("SimpleDateFormat")
    private void updateLastDate(long lastUpdate) {
        toolbar.setVisibleSubtitle(lastUpdate != -1);
        if (lastUpdate != -1) {
            SimpleDateFormat dt = new SimpleDateFormat(getString(R.string.last_update_format));
            ((ActionBarActivity) getActivity()).getSupportActionBar()
                    .setSubtitle(getString(R.string.last_update, dt.format(new Date(lastUpdate))));
        }
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        list.setOnItemClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_simple_list;
    }

    @Override
    public void onRefresh() {
        requestOnServer();
    }

    private void initializeLoader() {
        Loader loader = getActivity().getSupportLoaderManager().getLoader(LoaderConstants.DEVICE_LIST_LOADER);
        if (loader == null)
            getActivity().getSupportLoaderManager().initLoader(LoaderConstants.DEVICE_LIST_LOADER, null, this);
        else
            restartCursorLoader();
    }

    protected void restartCursorLoader() {
        getActivity().getSupportLoaderManager().restartLoader(LoaderConstants.DEVICE_LIST_LOADER, null, this);
    }

    protected void requestOnServer() {
        SmartAirApplication.getInstance().getSpiceManager().execute(new GetDevicesRequest(DEFAULT_USER),
                new SimpleRetrofitCallback<GetDevicesResponse>() {
            @Override
            public void onRequestSuccess(GetDevicesResponse devices) {
                SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();
                for (Device device : devices)
                    db.replace(Device.Contract.TABLE_NAME, null, device.toCV());

                restartCursorLoader();
                long time = System.currentTimeMillis();
                updateLastDate(time);
                preferences.edit().putLong(UPDATE_KEY, time).apply();
            }

            @Override
            public void onRequestFailure(SpiceException spiceException) {
                super.onRequestFailure(spiceException);
                getSwipeRefreshLayout().setRefreshing(false);
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), StatisticActivity.class);
        intent.putExtra(IntentConstants.DEVICE_ID, ((Cursor) parent.getItemAtPosition(position)).getString(0));
        startActivity(intent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), SmartAirProvider.DEVICE_CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        ((DeviceCursorAdapter)list.getAdapter()).swapCursor(data);
        getSwipeRefreshLayout().setRefreshing(false);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    @Override
    public void onDestroy() {
        getActivity().getSupportLoaderManager().destroyLoader(LoaderConstants.DEVICE_LIST_LOADER);
        super.onDestroy();
    }
}
