package com.oragee.groups.base;

import android.app.Activity;
import android.util.Log;

import java.util.Stack;

/**
 * Created by DoggieX on 2017/8/24.
 */

public class ActivityManager {

    private static Stack<Activity> mActivityStack;
    private static ActivityManager instance;

    static {
        instance = new ActivityManager();
    }

    public static ActivityManager getInstance() {
        return instance;
    }

    /**
     * 添加activity到栈
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<Activity>();
        }
        mActivityStack.add(activity);
        Log.e("ActivityManager", "mActivityStack.size():" + mActivityStack.size());
    }

    /**
     * 获取当前activity
     */
    public Activity getCurrentActivity() {
        Activity activity = mActivityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前activity
     */
    public void finishActivity() {
        Activity activity = mActivityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null && mActivityStack != null) {
            mActivityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定class的activity
     *
     * @param clazz
     */
    public void finishActivity(Class<?> clazz) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(clazz)) {
                finishActivity(activity);
                return;
            }
        }
    }

    /**
     * 移除Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        for (Activity activity1 : mActivityStack) {
            if (activity1 == activity) {
                mActivityStack.remove(activity);
                return;
            }
        }
    }

    /**
     * 移除Activity
     *
     * @param clz
     */
    public void removeActivity(Class clz) {
        for (Activity activity1 : mActivityStack) {
            if (activity1.getClass().equals(clz)) {
                mActivityStack.remove(activity1);
                return;
            }
        }
    }

    /**
     * 结束全部activity
     */
    private void finishAllActivities() {
        for (Activity activity : mActivityStack) {
            if (activity != null) {
                finishActivity(activity);
            }
        }
        mActivityStack.clear();
    }

    /**
     * 结束除当前Activity之外的全部Activity
     */
    public void finishOtherActivities(){
        mActivityStack.pop();
        finishAllActivities();
    }

    public boolean getIsActivityActive(Class clz) {
        for (Activity activity : mActivityStack) {
            if (clz.equals(activity.getClass())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 退出程序
     */
    public void exitApp() {
        finishAllActivities();
        System.exit(0);
    }
}
