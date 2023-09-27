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

package com.ethan.hydrogen.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Keep;

/**
 * @ClassName: InputMethodManagerUtil.java
 * @Description: 软键盘工具包
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/11/11 20:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/11/11 20:24
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
@Keep
public class InputMethodManagerUtil {
    private InputMethodManager inputMethodManager;

    private InputMethodManagerUtil() {
        if (Inner.INSTANCE != null) {
            throw new RuntimeException("该实例已存在，请通过getInstance方法获取");
        }
    }

    private static class Inner {
        private static final InputMethodManagerUtil INSTANCE = new InputMethodManagerUtil();
    }

    /**
     * 返回一个对象
     *
     * @param context
     * @return
     */
    public static InputMethodManagerUtil getInstance(Context context) {
        Inner.INSTANCE.inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return Inner.INSTANCE;
    }

    /**
     * 打开软键盘
     */
    public void toggleSoftInput() {
        if (inputMethodManager.isActive()) {
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 隐藏软键盘 当前页面必须有View获得焦点，否则会报空指针
     *
     * @param activity 软键盘所在的页面
     */
    public void hideSoftInput(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
