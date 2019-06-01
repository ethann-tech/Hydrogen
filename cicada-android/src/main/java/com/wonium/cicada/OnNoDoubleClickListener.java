package com.wonium.cicada;

import android.view.View;


import java.util.Calendar;

/**
 * @ClassName: OnNoDoubleClickListener
 * @Description: 不能双击的事件监听器
 * @author: Ethan
 * @E-mail: wonium@qq.com
 * @Blog:  blog.wonium.com
 * @CreateDate: 2019/6/1 17:54
 * @UpdateUser:  ethan
 * @UpdateDate: 2019/6/1 17:54
 * @UpdateDescription: 添加
 * @Version: v0.1.8
 */
public abstract class OnNoDoubleClickListener implements View.OnClickListener{
    /**
     * 最小点击时间间隔
     */
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        }
    }

    /**
     * 结果回调方法
     * @param v 被点击对象
     */
    public abstract void onNoDoubleClick(View v);
}
