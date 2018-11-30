package com.oragee.groups.util;

import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.widget.ImageView;

import com.oragee.groups.R;
import com.oragee.groups.app.App;

import java.lang.reflect.Field;

/**
 * @author 何泰樑
 * @ClassName BottomNavigationViewHelper
 * @Description
 * @date 2018-11-7
 * @history 2018-11-7 author: description:
 */
public class BottomNavigationViewHelper {

    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        //获取子View BottomNavigationMenuView的对象
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            //设置私有成员变量mShiftingMode可以修改
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //去除shift效果
                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
                ImageView imageView = item.findViewById(android.support.design.R.id.icon);
                imageView.getLayoutParams().width = (int) App.getAppInstance().getResources().getDimension(R.dimen.dp_17);
                imageView.getLayoutParams().height = (int) App.getAppInstance().getResources().getDimension(R.dimen.dp_17);

            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "没有mShiftingMode这个成员变量", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "无法修改mShiftingMode的值", e);
        }
    }


}
