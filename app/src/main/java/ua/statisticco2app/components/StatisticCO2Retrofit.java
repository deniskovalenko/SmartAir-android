package ua.statisticco2app.components;

import retrofit.http.GET;
import retrofit.http.Query;
import ua.statisticco2app.models.responses.GetDevicesResponse;
import ua.statisticco2app.models.responses.GetStatisticResponse;

public interface StatisticCO2Retrofit {

    @GET("/api/devices")
    public GetDevicesResponse getDevices(@Query("userid") long userId);

    @GET("/json_statistic")
    public GetStatisticResponse getStatistic();
}
