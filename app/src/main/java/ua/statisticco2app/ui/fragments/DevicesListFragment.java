package ua.statisticco2app.ui.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.octo.android.robospice.persistence.exception.SpiceException;

import butterknife.InjectView;
import ua.statisticco2app.R;
import ua.statisticco2app.StatisticCO2Application;
import ua.statisticco2app.adapters.DeviceArrayAdapter;
import ua.statisticco2app.components.SimpleRetrofitCallback;
import ua.statisticco2app.constants.IntentConstants;
import ua.statisticco2app.models.entities.Device;
import ua.statisticco2app.models.requests.GetDevicesRequest;
import ua.statisticco2app.models.responses.GetDevicesResponse;
import ua.statisticco2app.ui.activities.StatisticActivity;

public class DevicesListFragment extends BaseRefreshFragment implements AdapterView.OnItemClickListener {

    @InjectView(R.id.list)
    ListView list;

    protected final String DEFAULT_USER = "700caba5-9d40-4d34-9d6c-b15e40c5425e";

    public static DevicesListFragment getInstance() {
        return new DevicesListFragment();
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initView();
        requestOnServer();
    }

    protected void initView() {
        list.setAdapter(new DeviceArrayAdapter(getActivity()));
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

    protected void requestOnServer() {
        StatisticCO2Application.getInstance().getSpiceManager().execute(new GetDevicesRequest(DEFAULT_USER),
                new SimpleRetrofitCallback<GetDevicesResponse>() {
            @Override
            public void onRequestSuccess(GetDevicesResponse devices) {
                DeviceArrayAdapter adapter = (DeviceArrayAdapter) list.getAdapter();
                adapter.clear();
                adapter.addAll(devices);
                getSwipeRefreshLayout().setRefreshing(false);
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
        Device device = (Device) parent.getItemAtPosition(position);
        intent.putExtra(IntentConstants.DEVICE_ID, device.getDevice_id());
        startActivity(intent);
    }
}
