package com.smartair.app.models.requests;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.smartair.app.components.SmartAirRetrofit;
import com.smartair.app.models.responses.SendGcmTokenResponse;

/**
 * Created by denis on 22.11.15.
 */
public class SendGcmTokenRequest extends RetrofitSpiceRequest<SendGcmTokenResponse, SmartAirRetrofit> {

    private final String gcmToken;
    private final String userId;

    public SendGcmTokenRequest(String userId, String gcmToken) {
        super(SendGcmTokenResponse.class, SmartAirRetrofit.class);
        this.userId = userId;
        this.gcmToken = gcmToken;
    }

    @Override
    public SendGcmTokenResponse loadDataFromNetwork() throws Exception {
        return getService().sendGcmToken(userId, gcmToken);
    }
}
