package com.smartair.app.components;

import retrofit.http.GET;
import retrofit.http.Query;
import com.smartair.app.models.responses.GetDevicesResponse;
import com.smartair.app.models.responses.GetStatisticResponse;

public interface SmartAirRetrofit {

    @GET("/api/devices")
    GetDevicesResponse getDevices(@Query("userId") String userId);

    @GET("/api/statistic")
    GetStatisticResponse getStatistic(@Query("deviceId") String deviceId, @Query("startDate") long startDate, @Query("endDate") long endDate);
}
