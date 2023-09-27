package com.ethan.hydrogen.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName:
 * @Description: 判断是否为异形屏 （刘海屏，水滴屏等）
 * @Author: Ethan
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2019-09-17 09:05
 * @UpdateUser: ethan
 * @UpdateDate: 2019-09-17 09:05
 * UpdateDescription: 更新说明
 * @Version: 1.0.0
 **/
public class NotchScreenUtil {
    /**
     * 刘海屏、水滴屏等异型屏支持的Android系统版本：8.0-》全面屏  8.0以上-》刘海屏、水滴屏等异型屏
     * true 支持，false 不支持
     */
    private NotchScreenUtil() {
        if (Inner.INSTANCE != null) {
            throw new RuntimeException("该实例已存在，请通过getInstance方法获取");
        }
    }

    private static class Inner {
        private static final NotchScreenUtil INSTANCE = new NotchScreenUtil();
    }

    public static NotchScreenUtil getInstance() {
        return Inner.INSTANCE;
    }


    public boolean isNotchSupportVersion(Context context) {

        // 低于 API 21的，都不会是全面屏。。。
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return false;
        }
        return (isAllScreenDevice(context) || isNotch(context));
    }


    /**
     * 判断是否是全面屏
     */
    private volatile static boolean mHasCheckAllScreen;
    private volatile static boolean mIsAllScreenDevice;

    public boolean isAllScreenDevice(Context context) {
        if (mHasCheckAllScreen) {
            return mIsAllScreenDevice;
        }
        mHasCheckAllScreen = true;
        mIsAllScreenDevice = false;
        // 低于 API 21的，都不会是全面屏。。。
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return false;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            Display display = windowManager.getDefaultDisplay();
            Point point = new Point();
            display.getRealSize(point);
            float width, height;
            if (point.x < point.y) {
                width = point.x;
                height = point.y;
            } else {
                width = point.y;
                height = point.x;
            }
            if (height / width >= 1.97f) {
                mIsAllScreenDevice = true;
            }
        }
        return mIsAllScreenDevice;
    }

    /**
     * 检查流行机型是否存在刘海屏
     *
     * @param context 上下文
     * @return true 存在 false 不存
     */
    public boolean isNotch(Context context) {
        return isNotch_VIVO(context) || isNotch_OPPO(context) || isNotch_HUAWEI(context) || isNotch_XIAOMI(context);
    }


    /**
     * 检查vivo是否存在刘海屏、水滴屏等异型屏
     *
     * @param context
     * @return
     */
    public boolean isNotch_VIVO(Context context) {
        boolean isNotch = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class cls = cl.loadClass("android.util.FtFeature");
            Method method = cls.getMethod("isFeatureSupport", int.class);
            // 0x00000020：是否有刘海  0x00000008：是否有圆角
            isNotch = (boolean) method.invoke(cls, 0x00000020);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            return isNotch;
        }
    }

    /**
     * 检查oppo是否存在刘海屏、水滴屏等异型屏
     *
     * @param context
     * @return
     */
    public boolean isNotch_OPPO(Context context) {
        boolean isNotch = false;
        try {
            isNotch = context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return isNotch;
        }
    }

    /**
     * 检查huawei是否存在刘海屏、水滴屏等异型屏
     *
     * @param context
     * @return
     */
    public boolean isNotch_HUAWEI(Context context) {
        boolean isNotch = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class cls = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method method = cls.getMethod("hasNotchInScreen");
            isNotch = (boolean) method.invoke(cls);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return isNotch;
        }
    }

    /**
     * 检查xiaomi是否存在刘海屏、水滴屏等异型屏
     */

    public boolean isNotch_XIAOMI(Context context) {
        boolean isNotch = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class cls = cl.loadClass("android.os.SystemProperties");
            Method method = cls.getMethod("getInt", String.class, int.class);
            isNotch = ((int) method.invoke(null, "ro.miui.notch", 0) == 1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            return isNotch;
        }
    }


}
