package com.ethan.hydrogen.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.Display;

import java.lang.reflect.Method;

public class NavigationBarUtil {
    /**
     * 获取虚拟键的高度
     *
     * @param context 上下文
     * @return 虚拟键的高度
     */
    public static int getNavigationBarHeight(Activity context) {
        int result = 0;
        if(hasNavBar(context)) {
            Resources res = context.getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if(resourceId > 0 && isNavigationBarShow(context)) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

    /**
     * 判断是否有虚拟键
     *
     * @param context 上下文
     * @return 是否有虚拟键
     */
    public static boolean hasNavBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if(id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class<?> systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasNavigationBar;
    }

    public static boolean isNavigationBarShow(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        Point realSize = new Point();
        display.getSize(size);
        display.getRealSize(realSize);
        return realSize.y != size.y;
    }
}
