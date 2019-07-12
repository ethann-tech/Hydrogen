package com.wonium.cicada.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermissionsUtil {
        private PermissionsUtil mInstance;

    public PermissionsUtil getInstance() {
        if (mInstance ==null){
            synchronized (PermissionsUtil.class){
                if (mInstance==null){
                    mInstance =new PermissionsUtil();
                }
            }
        }
        return mInstance;
    }

    /**
     * 判断应用是否申明某个权限
     * @param context 上下文
     * @param permission Manifest.permission里的值
     * @return 是否申明某个权限
     */
    public  boolean hasPermission(Context context, String permission){
        return (PackageManager.PERMISSION_GRANTED) == (context.getPackageManager().checkPermission(permission, context.getPackageName()));
    }

    /**
     * 获得应用申明的所有权限列表
     * @param context 上下文
     * @return 获得应用申明的所有权限列表
     */
    public  List<String> getPermissions(Context context){
        List<String> permissions=new ArrayList<String>();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
            permissions.addAll(Arrays.asList(packageInfo.requestedPermissions));

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return permissions;
    }

}
