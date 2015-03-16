package ua.statisticco2app.models.requests;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import ua.statisticco2app.components.StatisticCO2Retrofit;
import ua.statisticco2app.models.responses.GetStatisticResponse;

public class GetStatisticRequest extends RetrofitSpiceRequest<GetStatisticResponse, StatisticCO2Retrofit> {

    public GetStatisticRequest() {
        super(GetStatisticResponse.class, StatisticCO2Retrofit.class);
    }

    @Override
    public GetStatisticResponse loadDataFromNetwork() throws Exception {
        return getService().getStatistic();
    }
}
