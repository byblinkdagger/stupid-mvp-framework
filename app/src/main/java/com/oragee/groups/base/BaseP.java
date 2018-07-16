package com.oragee.groups.base;

import android.content.Context;

import java.util.LinkedList;

import io.reactivex.disposables.Disposable;

/**
 * Created by DoggieX on 2017/7/27.
 */

public abstract class BaseP implements IP {

    protected LinkedList<Disposable> subscriptions = new LinkedList<>();

    protected Context mContext;

}
