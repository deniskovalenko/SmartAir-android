package ua.ibreathe.components;

import retrofit.http.GET;
import retrofit.http.Query;
import ua.ibreathe.models.responses.GetDevicesResponse;
import ua.ibreathe.models.responses.GetStatisticResponse;

public interface StatisticCO2Retrofit {

    @GET("/api/devices")
    GetDevicesResponse getDevices(@Query("userId") String userId);

    @GET("/json_statistic")
    GetStatisticResponse getStatistic();
}
