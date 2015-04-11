package com.smartair.app.ui.fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;

import butterknife.InjectView;
import com.smartair.app.R;
import com.smartair.app.SmartAirApplication;
import com.smartair.app.adapters.IndicationArrayAdapter;
import com.smartair.app.components.SimpleRetrofitCallback;
import com.smartair.app.models.requests.GetStatisticRequest;
import com.smartair.app.models.responses.GetStatisticResponse;

@Deprecated
public class SimpleFragment extends BaseRefreshFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = SimpleFragment.class.getSimpleName();

    public static SimpleFragment getInstance() {
        return new SimpleFragment();
    }

    @InjectView(R.id.list)
    ListView list;

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

    private SimpleRetrofitCallback<GetStatisticResponse> callback = new SimpleRetrofitCallback<GetStatisticResponse>() {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
//            super.onRequestFailure(spiceException);
            setRefreshing(false);
            Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(GetStatisticResponse indications) {
            setRefreshing(false);
            IndicationArrayAdapter adapter = ((IndicationArrayAdapter)list.getAdapter());
            adapter.clear();
            adapter.addAll(indications);

        }
    };

    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh");
        SmartAirApplication.getInstance().getSpiceManager().execute(new GetStatisticRequest(), callback);
    }
}
