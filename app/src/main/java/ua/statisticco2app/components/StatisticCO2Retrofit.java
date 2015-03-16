package ua.statisticco2app.components;

import retrofit.http.GET;
import ua.statisticco2app.models.responses.GetStatisticResponse;

public interface StatisticCO2Retrofit {

    @GET("/json_statistic")
    public GetStatisticResponse getStatistic();
}
