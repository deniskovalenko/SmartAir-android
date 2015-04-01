package ua.statisticco2app.ui.fragments;

import android.support.v4.widget.SwipeRefreshLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.octo.android.robospice.persistence.exception.SpiceException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.InjectView;
import ua.statisticco2app.R;
import ua.statisticco2app.StatisticCO2Application;
import ua.statisticco2app.components.SimpleRetrofitCallback;
import ua.statisticco2app.models.entities.Indication;
import ua.statisticco2app.models.requests.GetStatisticRequest;
import ua.statisticco2app.models.responses.GetStatisticResponse;

public class ChartStatisticFragment extends BaseRefreshFragment implements SwipeRefreshLayout.OnRefreshListener {
    @InjectView(R.id.chartTemperature)
    LineChart chartTemperature;

    @InjectView(R.id.chartCO2)
    LineChart chartCO2;
    private XAxis xAxis;
    private YAxis yAxis;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chart_statistic;
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initView();
        requestOnServer();
    }

    protected void initView() {
        xAxis = chartTemperature.getXAxis();
        yAxis = chartTemperature.getAxisLeft();
    }

    @Override
    public void onRefresh() {
        requestOnServer();
    }

    protected void requestOnServer() {
        StatisticCO2Application.getInstance().getSpiceManager().execute(new GetStatisticRequest(), new SimpleRetrofitCallback<GetStatisticResponse>() {

            @Override
            public void onRequestFailure(SpiceException spiceException) {
                super.onRequestFailure(spiceException);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onRequestSuccess(GetStatisticResponse indications) {
                swipeRefreshLayout.setRefreshing(false);

//                SimpleDateFormat formatDate = new SimpleDateFormat("hh:mm");
                List<String> xData = new ArrayList<>();
                List<Entry> temperatures = new ArrayList<>();
                int index = 0;

                for (Indication indication : indications) {
                    xData.add(indication.getDate());
                    temperatures.add(new Entry(indication.getTemperature(), index++));
                }


//                LineDataSet xDataSet = new LineDataSet(indications, "values");
//                xAxis.
            }
        });
    }
}
