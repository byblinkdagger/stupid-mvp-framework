package com.oragee.groups.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by DoggieX on 2017/7/27. Modified by lucky 17/11/24
 * iview for activity
 */

public interface IV extends LifecycleProvider<ActivityEvent> {
    Bundle getIntentExtra();

    void setP();

    Context getContext();

    View getRootView();

    void showSimpleLoading(String msg);

    void endSimpleLoading();
}
