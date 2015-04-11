package com.smartair.app.models.requests;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import com.smartair.app.components.SmartAirRetrofit;
import com.smartair.app.models.responses.GetStatisticResponse;

public class GetStatisticRequest extends RetrofitSpiceRequest<GetStatisticResponse, SmartAirRetrofit> {

    public GetStatisticRequest() {
        super(GetStatisticResponse.class, SmartAirRetrofit.class);
    }

    @Override
    public GetStatisticResponse loadDataFromNetwork() throws Exception {
        return getService().getStatistic();
    }
}
