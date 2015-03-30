package ua.statisticco2app.ui.fragments;

import android.support.v4.widget.SwipeRefreshLayout;

import com.github.mikephil.charting.charts.LineChart;

import butterknife.InjectView;
import ua.statisticco2app.R;

public class ChartStatisticFragment extends BaseRefreshFragment implements SwipeRefreshLayout.OnRefreshListener {
    @InjectView(R.id.chartTemperature)
    LineChart chartTemperature;

    @InjectView(R.id.chartCO2)
    LineChart chartCO2;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chart_statistic;
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();

    }

    @Override
    public void onRefresh() {

    }
}
