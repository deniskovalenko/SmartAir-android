package ua.statisticco2app.ui.fragments;

import android.widget.ListView;

import com.octo.android.robospice.persistence.exception.SpiceException;

import butterknife.InjectView;
import ua.statisticco2app.R;
import ua.statisticco2app.StatisticCO2Application;
import ua.statisticco2app.adapters.DeviceArrayAdapter;
import ua.statisticco2app.components.SimpleRetrofitCallback;
import ua.statisticco2app.models.requests.GetDevicesRequest;
import ua.statisticco2app.models.responses.GetDevicesResponse;

public class DevicesListFragment extends BaseRefreshFragment {

    @InjectView(R.id.list)
    ListView list;

    protected final long DEFAULT_USER = 550;

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
                ((DeviceArrayAdapter)list.getAdapter()).addAll(devices);
                getSwipeRefreshLayout().setRefreshing(false);
            }

            @Override
            public void onRequestFailure(SpiceException spiceException) {
                super.onRequestFailure(spiceException);
                getSwipeRefreshLayout().setRefreshing(false);
            }
        });
    }


}
