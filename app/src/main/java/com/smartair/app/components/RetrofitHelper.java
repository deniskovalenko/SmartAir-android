package com.smartair.app.components;

import com.google.gson.Gson;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import com.smartair.app.BuildConfig;
import com.smartair.app.constants.RestConstants;

public class RetrofitHelper {

    public static RestAdapter.Builder createStatisticCO2RestAdapter(RestAdapter.Builder builder) {
        return createBaseRestAdapter(builder).setEndpoint(RestConstants.BASE_URL);
    }

    public static RestAdapter.Builder createBaseRestAdapter(RestAdapter.Builder builder) {
        if (builder == null)
            builder = new RestAdapter.Builder();

        builder.setConverter(new GsonConverter(new Gson()));


        if (BuildConfig.DEBUG) {
            builder.setLogLevel(RestAdapter.LogLevel.FULL);
        } else {
            builder.setLogLevel(RestAdapter.LogLevel.NONE);
        }

        return builder;
    }
}