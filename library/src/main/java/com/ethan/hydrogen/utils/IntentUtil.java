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

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.Keep;

/**
 * @ClassName: IntentUtil.java
 * @Description: 跳转方法
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/11/11 20:16
 * @UpdateUser: updateUser
 * @UpdateDate: 2018/11/11 20:16
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
@Keep
public class IntentUtil {

    private IntentUtil() {}

    /**
     * 实例对象
     */
    private static class Inner {
        private static final IntentUtil INSTANCE = new IntentUtil();
    }

    public static IntentUtil getInstance() {
        return Inner.INSTANCE;
    }

    /**
     * 跳转到拨号页面
     *
     * @param context 上下文
     * @param phone   手机号
     */
    public void callPhone(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        context.startActivity(intent);
    }
}
