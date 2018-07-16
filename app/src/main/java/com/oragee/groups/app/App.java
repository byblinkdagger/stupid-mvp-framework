package com.oragee.groups.app;

import android.app.Application;

import com.simple.spiderman.CrashModel;
import com.simple.spiderman.SpiderMan;


/**
 * Created by DoggieX on 2017/7/26.
 */

public class App extends Application {

    private static Application appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        SpiderMan.getInstance()
                .init(this)
                //设置是否捕获异常，不弹出崩溃框
                .setEnable(true)
                //设置是否显示崩溃信息展示页面
                .showCrashMessage(true)
                //是否回调异常信息，友盟等第三方崩溃信息收集平台会用到,
                .setOnCrashListener(new SpiderMan.OnCrashListener() {
                    @Override
                    public void onCrash(Thread t, Throwable ex, CrashModel model) {
                        //CrashModel 崩溃信息记录，包含设备信息
                    }
                });
    }

    public static Application getAppInstance() {
        return appInstance;
    }

}
