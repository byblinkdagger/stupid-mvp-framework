package com.oragee.groups.net;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lucky on 2017/4/19.
 */
public class RetrofitScheduler {

    /**
     * 线程调度
     */
    public static ObservableTransformer schedulersTransformer() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
