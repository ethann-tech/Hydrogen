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
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermissionsUtil {
    private PermissionsUtil() {
    }

    private static class Inner {
        private static final PermissionsUtil INSTANCE = new PermissionsUtil();
    }

    public PermissionsUtil getInstance() {
        return Inner.INSTANCE;
    }

    /**
     * 判断应用是否申明某个权限
     *
     * @param context    上下文
     * @param permission Manifest.permission里的值
     * @return 是否申明某个权限
     */
    public boolean hasPermission(Context context, String permission) {
        return (PackageManager.PERMISSION_GRANTED) == (context.getPackageManager().checkPermission(permission, context.getPackageName()));
    }

    /**
     * 获得应用申明的所有权限列表
     *
     * @param context 上下文
     * @return 获得应用申明的所有权限列表
     */
    public List<String> getPermissions(Context context) {
        List<String> permissions = new ArrayList<String>();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
            permissions.addAll(Arrays.asList(packageInfo.requestedPermissions));

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return permissions;
    }

}
