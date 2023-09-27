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


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import androidx.annotation.Keep;

/**
 * @ClassName: DeviceUtil.java
 * @Description: 类描述
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/11/11 20:12
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/11/11 20:12
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
@Keep
public class DeviceUtil {

    private DeviceUtil() {
        if (Inner.INSTANCE != null) {
            throw new RuntimeException("该实例已存在，请通过getInstance方法获取");
        }
    }
    private static class Inner {
        private static final DeviceUtil INSTANCE = new DeviceUtil();
    }

    /**
     * 实例对象
     */
    public static DeviceUtil getInstance() {
        return Inner.INSTANCE;
    }

    /**
     * 获取设备IMEI
     *
     * @param activity
     * @return
     */

    @SuppressLint("MissingPermission")
    public String getDeviceIMEI(Activity activity) {
        TelephonyManager manager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
        return manager.getDeviceId();
    }


    /**
     * 获取MacAddr
     *
     * @return macAddress
     */

    @SuppressLint("HardwareIds")
    public String getMacAddress(Context context) {
        String macAddress = "00:00:00:00:00:00";
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                WifiManager manager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                if (manager != null) {
                    WifiInfo info = manager.getConnectionInfo();
                    if (info != null) {
                        macAddress = info.getMacAddress();
                    }
                }
            } else {
                InetAddress ip = getLocalInetAddress();
                byte[] b = NetworkInterface.getByInetAddress(ip).getHardwareAddress();
                StringBuilder buffer = new StringBuilder();
                for (int i = 0; i < b.length; i++) {
                    if (i != 0) {
                        buffer.append(':');
                    }
                    String str = Integer.toHexString(b[i] & 0xFF);
                    buffer.append(str.length() == 1 ? 0 + str : str);
                }
                macAddress = buffer.toString().toUpperCase();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return macAddress.toLowerCase();
    }

    private InetAddress getLocalInetAddress() {
        InetAddress ip = null;
        try {
            // 列举
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            // 是否还有元素
            while (networkInterfaces.hasMoreElements()) {
                // 得到下一个元素
                NetworkInterface ni = networkInterfaces.nextElement();
                // 得到一个ip地址的列举
                Enumeration<InetAddress> enIp = ni.getInetAddresses();
                while (enIp.hasMoreElements()) {
                    ip = enIp.nextElement();
                    if (!ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {
                        break;
                    } else {
                        ip = null;
                    }
                }
                if (ip != null) {
                    break;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ip;
    }

    /**
     * 获取手机号
     *
     * @param context 上下文
     * @return 手机号
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public String getPhoneNumber(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        assert manager != null;
        return manager.getLine1Number();
    }

    /**
     * 获取手机品牌
     *
     * @return Build.BRAND
     */
    public String getBrand() {
        return Build.BRAND;
    }

    /**
     * 获得手机名称
     *
     * @return 手机名称
     */
    public String getMobileName() {
        return Build.MANUFACTURER + " " + Build.MODEL;
    }

    /**
     * 获取手机型号
     *
     * @return Build.MODEL
     */
    public String getModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机唯一的id
     *
     * @return Settings.Secure.Android_ID
     */
    public String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * 获得系统版本号
     *
     * @return 系统版本号
     */
    public int getOSVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获得序列号
     *
     * @return 序列号
     */
    public String getSerialNum() {
        String serialNum = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            serialNum = (String) (get.invoke(c, "ro.serialno", "unknown"));
        } catch (Exception ignored) {
        }

        return serialNum;
    }

}

