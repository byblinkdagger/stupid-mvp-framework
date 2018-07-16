package com.oragee.groups.net;


import com.oragee.groups.event.LoginEvent;
import com.oragee.groups.net.bean.BaseRes;
import com.oragee.groups.util.SafeString;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by karel on 2017/5/15.
 */

public class ApiFunction<T> implements Function<BaseRes<T>, T> {
    @Override
    public T apply(@NonNull BaseRes<T> tBaseApiResult) throws Exception {
        if (tBaseApiResult.isTokenTimeout()) {
            EventBus.getDefault().post(new LoginEvent.OnLogoutEvent());
            throw new ApiException(tBaseApiResult.getCode(), SafeString.getString(tBaseApiResult.getMessage()));
        }
        if (!tBaseApiResult.isSuccess()) {
            throw new ApiException(tBaseApiResult.getCode(), SafeString.getString(tBaseApiResult.getMessage()));
        }
        return tBaseApiResult.getData();
    }
}
