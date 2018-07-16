package com.oragee.groups.net;

import android.accounts.NetworkErrorException;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.oragee.groups.R;
import com.oragee.groups.app.App;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by karel on 2017/5/15.
 */

public abstract class ApiSubscriber<T> implements Observer<T> {
    private Disposable mDisposable;
    @Override
    public void onComplete() {
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        ApiException apiException;
        if (e instanceof ApiException) {
            apiException = (ApiException) e;
        } else if (e instanceof NetworkErrorException) {
            apiException = new ApiException(ApiException.CODE_NO_NET_ERROR);
        } else {
            apiException = new ApiException(ApiException.CODE_UNKNOWN_ERROR);
        }
        onApiFailed(apiException);
        onFinish();
    }

    public void onApiFailed(ApiException apiException) {
        Log.d("luck","apiException.getErrorCode() :"+apiException.getErrorCode());
        Log.d("luck","apiException.getErrorMessage() :"+apiException.getErrorMessage());
        switch (apiException.getErrorCode()) {
            case ApiException.CODE_NO_NET_ERROR:
//                Toast.makeText(App.getAppInstance(), R.string.error_network, Toast.LENGTH_SHORT).show();
                break;
            case ApiException.CODE_SERVER_ERROR:
//                Toast.makeText(App.getAppInstance(), R.string.error_server, Toast.LENGTH_SHORT).show();
                break;
            case ApiException.CODE_TOKEN_ERROR:
//                Toast.makeText(App.getAppInstance(), R.string.error_token, Toast.LENGTH_SHORT).show();
                break;
            case ApiException.CODE_UNKNOWN_ERROR:
//                Toast.makeText(App.getAppInstance(), R.string.unkown_error, Toast.LENGTH_SHORT).show();
                break;
            case ApiException.CODE_TOKEN_ILLEAGAL:
//                Toast.makeText(App.getAppInstance(), R.string.illegal_token, Toast.LENGTH_SHORT).show();
                break;
            default:
                String errorMsg = apiException.getErrorMessage();
                if (TextUtils.isEmpty(errorMsg)) {
//                    Toast.makeText(App.getAppInstance(), R.string.unkown_error, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(App.getAppInstance(), errorMsg, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * Error or Completed
     */
    public void onFinish() {
        mDisposable.dispose();
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }
}
