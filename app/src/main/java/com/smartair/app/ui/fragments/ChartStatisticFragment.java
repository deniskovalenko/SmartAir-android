package com.smartair.app.ui.fragments;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.InjectView;
import com.smartair.app.R;
import com.smartair.app.SmartAirApplication;
import com.smartair.app.components.SimpleRetrofitCallback;
import com.smartair.app.constants.IntentConstants;
import com.smartair.app.models.entities.Indication;
import com.smartair.app.models.requests.GetStatisticRequest;
import com.smartair.app.models.responses.GetStatisticResponse;

public class ChartStatisticFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @InjectView(R.id.chartTemperature)
    LineChart chartTemperature;


    public static ChartStatisticFragment getInstance() {
        return new ChartStatisticFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chart_statistic;
    }

    @Override
    protected void onViewCreated() {
        initView();
        requestOnServer();
    }

    @Override
    protected void setListeners() {

    }

    protected void initView() {

        // no description text
        chartTemperature.setDescription("");

        // enable value highlighting
        chartTemperature.setHighlightEnabled(true);

        // enable touch gestures
        chartTemperature.setTouchEnabled(true);

        // enable scaling and dragging
        chartTemperature.setDragEnabled(true);
        chartTemperature.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chartTemperature.setPinchZoom(false);

        chartTemperature.setDrawGridBackground(false);

        XAxis x = chartTemperature.getXAxis();
        x.setEnabled(true);

        YAxis y = chartTemperature.getAxisLeft();
        y.setLabelCount(5);
        y.setEnabled(true);

        chartTemperature.getAxisRight().setEnabled(false);


//        // add data
//        setData(6, 50);
//
//        chartTemperature.getLegend().setEnabled(false);
//
//        chartTemperature.animateXY(2000, 2000);
//
//        // dont forget to refresh the drawing
//        chartTemperature.invalidate();
    }

    private void setData(int count, float range) {

        ArrayList<String> xVals = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            xVals.add("17:" + String.format("%2d", i * 10));
        }

        ArrayList<Entry> vals1 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult) + 20;
            vals1.add(new Entry(val, i));
        }

        // create a data object with the datasets
        LineDataSet set1 = createLineDataSet(vals1, "DataSet 1");
        LineData data = new LineData(xVals, set1);
        data.setValueTextSize(9f);
        data.setDrawValues(false);

        // set data
        chartTemperature.setData(data);
    }

    @Override
    public void onRefresh() {
        requestOnServer();
    }

    public LineDataSet createLineDataSet(ArrayList<Entry> vals1, String deacription) {
        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(vals1, deacription);
        set1.setDrawCubic(true);
        set1.setCubicIntensity(0.2f);
        //set1.setDrawFilled(true);
        set1.setDrawCircles(false);
        set1.setLineWidth(2f);
        set1.setCircleSize(5f);
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(Color.rgb(104, 241, 175));
        set1.setFillColor(ColorTemplate.getHoloBlue());
        return set1;
    }

    protected void requestOnServer() {
        SmartAirApplication.getInstance().getSpiceManager().execute(new GetStatisticRequest(getActivity().getIntent().getStringExtra(IntentConstants.DEVICE_ID)), new SimpleRetrofitCallback<GetStatisticResponse>() {

            @Override
            public void onRequestSuccess(GetStatisticResponse indications) {

//                SimpleDateFormat formatDate = new SimpleDateFormat("hh:mm");
                ArrayList<String> xData = new ArrayList<>();
                ArrayList<Entry> temperatures = new ArrayList<>();
                int index = 0;
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
                for (Indication indication : indications) {
                    xData.add(dateFormat.format(new Date(indication.getDate())));
                    temperatures.add(new Entry(indication.getCo2(), index++));
                }

                // create a dataset and give it a type
                LineDataSet set1 = createLineDataSet(temperatures, "Temperatures");

                // create a data object with the datasets
                LineData data = new LineData(xData, set1);
//                data.setValueTypeface(tf);
                data.setValueTextSize(9f);
                data.setDrawValues(false);
// set data
                chartTemperature.setData(data);
//                LineDataSet xDataSet = new LineDataSet(indications, "values");
//                xAxis.

                chartTemperature.getLegend().setEnabled(false);

                chartTemperature.animateXY(2000, 2000);
                chartTemperature.invalidate();
            }
        });
    }
}
