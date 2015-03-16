package ua.statisticco2app.ui.fragments;

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

public class SimpleFragment extends BaseFragment {

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
        StatisticCO2Application.getInstance().getSpiceManager().execute(new GetStatisticRequest(), callback);
    }

    @Override
    protected void setListeners() {

    }

    private SimpleRetrofitCallback<GetStatisticResponse> callback = new SimpleRetrofitCallback<GetStatisticResponse>() {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
//            super.onRequestFailure(spiceException);
            Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(GetStatisticResponse indications) {
            ((IndicationArrayAdapter)list.getAdapter()).addAll(indications);
        }
    };
}
