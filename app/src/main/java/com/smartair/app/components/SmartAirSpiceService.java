package com.smartair.app.components;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

import retrofit.RestAdapter;
import com.smartair.app.constants.RestConstants;

public class SmartAirSpiceService extends RetrofitGsonSpiceService {

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(SmartAirRetrofit.class);
    }

    @Override
    protected String getServerUrl() {
        return RestConstants.BASE_URL;
    }

    @Override
    protected RestAdapter.Builder createRestAdapterBuilder() {
        return RetrofitHelper.createStatisticCO2RestAdapter(super.createRestAdapterBuilder());
    }
}
