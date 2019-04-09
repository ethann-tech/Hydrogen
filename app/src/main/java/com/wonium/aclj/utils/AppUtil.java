package com.wonium.aclj.utils;


public class AppUtil {

    private static AppUtil mInstance;
    public static  AppUtil getInstance(){
        if (mInstance==null){
            synchronized (AppUtil.class){
                if (mInstance==null){
                    mInstance =new AppUtil();

                }
            }
        }
        return mInstance;
    }

}
