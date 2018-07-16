package com.oragee.groups.base;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

/**
 * Created by DoggieX on 2017/7/27. Modified by lucky 17/11/24
 * iview for fragment
 */

public interface IFV extends LifecycleProvider<FragmentEvent> {
    void setP();

    Context getContext();

    void showSimpleLoading(String msg);

    void endSimpleLoading();
}
