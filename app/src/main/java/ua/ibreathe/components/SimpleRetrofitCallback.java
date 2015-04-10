package ua.ibreathe.components;

import com.octo.android.robospice.exception.NetworkException;
import com.octo.android.robospice.exception.NoNetworkException;
import com.octo.android.robospice.exception.RequestCancelledException;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import retrofit.RetrofitError;

public abstract class SimpleRetrofitCallback<ResponseType> implements RequestListener<ResponseType> {

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        Throwable cause = spiceException.getCause();
        String message = (cause instanceof RetrofitError) ? handleRetrofitException((RetrofitError)cause) : handleRobospiceException(spiceException);
//        NotificationManager.notifyClients(NotificationManagerConstant.NOTIFY_RETROFIT_FAILURE_RESPONSE, 0, 0, message);
    }

    protected String handleRobospiceException(SpiceException spiceException) {
        if ((spiceException instanceof NoNetworkException)
                ||(spiceException instanceof RequestCancelledException)
                || (spiceException instanceof NetworkException))
            return spiceException.getMessage();
        return null;
    }

    protected String handleRetrofitException(RetrofitError retrofitError) {
//        if (retrofitError != null) {
//            return ((ServerError) retrofitError.getBodyAs(ServerError.class)).getMessage();
//        }
        return null;
    }

}
