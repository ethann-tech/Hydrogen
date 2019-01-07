package com.wonium.java.library.utils;

import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public enum  ToolUtil {
    /**
     * 实例对象
     */
    INSTANCE;

    /**
     * 判断是否点击的View 是EditView
     * @param v EditView的一个实例对象
     * @param event 事件
     * @return
     */
    public  boolean isHideInput(View v, MotionEvent event) {
        if ((v instanceof EditText)) {
            int[] location = {0, 0};
            v.getLocationInWindow(location);
            int left = location[0], top = location[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            return (event.getX() <= left) || (event.getX() >= right) || (event.getY() <= top) || (event.getY() >= bottom);
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }


}
