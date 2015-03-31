package ua.statisticco2app.models.requests;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import ua.statisticco2app.components.StatisticCO2Retrofit;
import ua.statisticco2app.models.responses.GetDevicesResponse;

public class GetDevicesRequest extends RetrofitSpiceRequest<GetDevicesResponse, StatisticCO2Retrofit> {

    private final String userId;

    public GetDevicesRequest(String userId) {
        super(GetDevicesResponse.class, StatisticCO2Retrofit.class);
        this.userId = userId;
    }

    @Override
    public GetDevicesResponse loadDataFromNetwork() throws Exception {
        return getService().getDevices(userId);
    }
}
