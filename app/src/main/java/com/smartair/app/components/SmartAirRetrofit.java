package com.smartair.app.components;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import com.smartair.app.models.responses.GetDevicesResponse;
import com.smartair.app.models.responses.GetStatisticResponse;
import com.smartair.app.models.responses.SendGcmTokenResponse;

public interface SmartAirRetrofit {

    @GET("/api/devices")
    GetDevicesResponse getDevices(@Query("userId") String userId);

    @GET("/api/statistic")
    GetStatisticResponse getStatistic(@Query("deviceId") String deviceId, @Query("startDate") long startDate, @Query("endDate") long endDate);

    @POST("/api/set_gcm_token")
    SendGcmTokenResponse sendGcmToken(@Query("userId") String userId, @Query("gcmToken") String gcmToken);

}
