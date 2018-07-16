package com.oragee.groups.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * modify by lucky on 2017/10/27.
 */

public abstract class BaseActivity<T extends IP> extends RxAppCompatActivity implements IV {

    protected T mP;
    private RxPermissions mRxPermissions;

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getContentLayout());
        ButterKnife.bind(this);

        ActivityManager.getInstance().addActivity(this);

        initStatusBar();
        setP();
        initView();
        initData();
    }

    @LayoutRes
    public abstract int getContentLayout();

    public abstract void initView();

    public abstract void initData();

    @Override
    public Bundle getIntentExtra() {
        return getIntent().getExtras();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityManager.getInstance().finishActivity(this);

        if (mLoadingDialog != null) {
            mLoadingDialog = null;
        }
    }

    @Override
    public View getRootView() {
        return ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
    }

    private void initStatusBar() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
////            tintManager = new SystemBarTintManager(this);
////            tintManager.setStatusBarTintColor(ContextCompat.getColor(this, R.color.statusbar_bg));
////            tintManager.setStatusBarTintEnabled(true);
//
//        }
    }

    private ProgressDialog mLoadingDialog;

    @Override
    public void showSimpleLoading(String msg) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new ProgressDialog(this);
            mLoadingDialog.setMessage(msg);
        }
        if (mLoadingDialog.isShowing()) {
            mLoadingDialog.setMessage(msg);
//            mLoadingDialog.notify();
        } else {
            mLoadingDialog.show();
        }
    }

    @Override
    public void endSimpleLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    public RxPermissions getRxPermissions() {
        if (mRxPermissions == null) {
            mRxPermissions = new RxPermissions(this);
        }
        return mRxPermissions;
    }

}
