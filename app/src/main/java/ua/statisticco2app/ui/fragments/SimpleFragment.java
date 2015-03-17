package ua.statisticco2app.ui.fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;

import butterknife.InjectView;
import ua.statisticco2app.R;
import ua.statisticco2app.StatisticCO2Application;
import ua.statisticco2app.adapters.IndicationArrayAdapter;
import ua.statisticco2app.components.SimpleRetrofitCallback;
import ua.statisticco2app.models.requests.GetStatisticRequest;
import ua.statisticco2app.models.responses.GetStatisticResponse;

public class SimpleFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = SimpleFragment.class.getSimpleName();

    public static SimpleFragment getInstance() {
        return new SimpleFragment();
    }

    @InjectView(R.id.list)
    ListView list;

    @InjectView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_simple_list;
    }

    @Override
    protected void onViewCreated() {
        Log.d(TAG, "onViewCreated");
        list.setAdapter(new IndicationArrayAdapter(getActivity()));
        onRefresh();
    }

    @Override
    protected void setListeners() {
        Log.d(TAG, "setListeners");
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_orange_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private SimpleRetrofitCallback<GetStatisticResponse> callback = new SimpleRetrofitCallback<GetStatisticResponse>() {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
//            super.onRequestFailure(spiceException);
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(GetStatisticResponse indications) {
            swipeRefreshLayout.setRefreshing(false);
            IndicationArrayAdapter adapter = ((IndicationArrayAdapter)list.getAdapter());
            adapter.clear();
            adapter.addAll(indications);

        }
    };

    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh");
        StatisticCO2Application.getInstance().getSpiceManager().execute(new GetStatisticRequest(), callback);
    }
}
