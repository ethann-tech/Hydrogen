package com.wonium.cicada.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class PackageInfoUtil {

    /**
     * 实例对象
     */
    private static PackageInfoUtil mInstance;
    public static PackageInfoUtil getInstance(){
        if (mInstance==null){
            synchronized (DeviceUtil.class){
                if (mInstance==null){
                    mInstance =new PackageInfoUtil();
                }
            }
        }
        return mInstance;
    }
    /**
     * 获取版本号
     * @param context
     * @return
     */
    public  int getVersionCode(Context context) {
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     *获取版本名
     * @param context
     * @return 版本名称
     */
    public  String getVersionName(Context context){
        PackageManager manager =context.getPackageManager();
        try {
            PackageInfo info =manager.getPackageInfo(context.getPackageName(),0);
            return  info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "1.0";
    }


    /**
     * 获取application层级的metadata
     *
     * @param context 上下文
     * @param key     key
     * @return value
     */
    public static String getApplicationMetaData(Context context, String key) {
        try {
            Bundle metaData = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData;
            return metaData.get(key).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
