package com.smartair.app.components;

import retrofit.http.GET;
import retrofit.http.Query;
import com.smartair.app.models.responses.GetDevicesResponse;
import com.smartair.app.models.responses.GetStatisticResponse;

public interface SmartAirRetrofit {

    @GET("/api/devices")
    GetDevicesResponse getDevices(@Query("userId") String userId);

    @GET("/json_statistic")
    GetStatisticResponse getStatistic();
}
