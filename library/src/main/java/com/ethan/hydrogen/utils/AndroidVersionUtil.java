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

import android.os.Build;


/**
 * @ClassName: ActivityManagerUtil.java
 * @Description: Activity统一管理工具包
 * @Author: ethan
 * @E-mail: ethan.nj@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/11/11 20:40
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/11/11 20:40
 * @UpdateDescription: 调整Size 方法名
 * @Version: 1.0.1
 */
public class AndroidVersionUtil {

    private AndroidVersionUtil() { }

    private static class Inner {
        private static final AndroidVersionUtil INSTANCE = new AndroidVersionUtil();
    }

    public static AndroidVersionUtil getInstance() {
        return Inner.INSTANCE;
    }

    /**
     * 是否在2.2版本及以上
     *
     * @return 是否在2.2版本及以上
     */
    public boolean isFroyo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    /**
     * 是否在2.3版本及以上
     *
     * @return 是否在2.3版本及以上
     */
    public boolean isGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    /**
     * 是否在2.3.3版本及以上
     *
     * @return 是否在2.3.3版本及以上
     */
    public boolean isGingerbreadMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1;
    }

    /**
     * 是否在3.0版本及以上
     *
     * @return 是否在3.0版本及以上
     */
    public boolean isHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    /**
     * 是否在3.1版本及以上
     *
     * @return 是否在3.1版本及以上
     */
    public boolean isHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    /**
     * 是否在4.0版本及以上
     *
     * @return 是否在4.0版本及以上
     */
    public boolean isIceCreamSandwich() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    /**
     * 是否在4.0.3版本及以上
     *
     * @return 是否在4.0.3版本及以上
     */
    public boolean isIceCreamSandwichMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1;
    }

    /**
     * 是否在4.1版本及以上
     *
     * @return 是否在4.1版本及以上
     */
    public boolean isJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    /**
     * 是否在4.4.2版本及以上
     *
     * @return 是否在4.4.2版本及以上
     */
    public static boolean isKitkat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    /**
     * 是否在5.0.1版本及以上
     *
     * @return 是否在5.0.1版本及以上
     */
    public boolean isLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
