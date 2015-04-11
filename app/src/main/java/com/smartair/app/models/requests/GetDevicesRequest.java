package com.smartair.app.models.requests;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import com.smartair.app.components.SmartAirRetrofit;
import com.smartair.app.models.responses.GetDevicesResponse;

public class GetDevicesRequest extends RetrofitSpiceRequest<GetDevicesResponse, SmartAirRetrofit> {

    private final String userId;

    public GetDevicesRequest(String userId) {
        super(GetDevicesResponse.class, SmartAirRetrofit.class);
        this.userId = userId;
    }

    @Override
    public GetDevicesResponse loadDataFromNetwork() throws Exception {
        return getService().getDevices(userId);
    }
}
