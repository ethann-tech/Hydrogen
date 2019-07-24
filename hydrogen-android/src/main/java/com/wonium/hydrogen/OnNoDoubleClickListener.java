/*
 * Copyright (C) 2019 Ethan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wonium.hydrogen;

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
