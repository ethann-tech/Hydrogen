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
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * 读取res目录下的内容的工具类
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-07-29
 * Time: 17:33
 */
public class ResourceUtil {
    public static final String LAYTOUT = "layout";
    public static final String DRAWABLE = "drawable";
    public static final String MIPMAP = "mipmap";
    public static final String MENU = "menu";
    public static final String RAW = "raw";
    public static final String ANIM = "anim";
    public static final String STRING = "string";
    public static final String STYLE = "style";
    public static final String STYLEABLE = "styleable";
    public static final String INTEGER = "integer";
    public static final String ID = "id";
    public static final String DIMEN = "dimen";
    public static final String COLOR = "color";
    public static final String BOOL = "bool";
    public static final String ATTR = "attr";


    private ResourceUtil() {
        if (Inner.INSTANCE != null) {
            throw new RuntimeException("该实例已存在，请通过getInstance方法获取");
        }
    }

    private static class Inner {
        private static final ResourceUtil INSTANCE = new ResourceUtil();
    }


    public ResourceUtil getInstance() {
        return Inner.INSTANCE;
    }

    /**
     * 根据资源名获得资源id
     *
     * @param context 上下文
     * @param name    资源名
     * @param type    资源类型
     * @return 资源id，找不到返回0
     */
    public int getResourceId(Context context, String name, String type) {
        Resources resources = null;
        PackageManager pm = context.getPackageManager();
        try {
            resources = context.getResources();
            return resources.getIdentifier(name, type, context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 从assets目录下读取文件内容
     *
     * @param context  上下文
     * @param fileName 文件名
     * @return 文件字节流
     */
    public byte[] readBytesFromAssets(Context context, String fileName) {
        InputStream is = null;
        byte[] buffer = null;
        try {
            is = context.getAssets().open(fileName);
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return buffer;
    }

    /**
     * 从res/raw目录下读取文件内容
     *
     * @param context 上下文
     * @param rawId   rawId
     * @return 文件字节流
     */
    public byte[] readBytesFromRaw(Context context, int rawId) {
        InputStream is = null;
        byte[] buffer = null;
        try {
            is = context.getResources().openRawResource(rawId);
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return buffer;
    }

    /**
     * 从assets目录读取文本
     *
     * @param context  上下文
     * @param fileName 文件名
     * @return 文本内容
     */
    public String readStringFromAssets(Context context, String fileName) {
        String result = null;
        byte[] buffer = readBytesFromAssets(context, fileName);
        try {
            result = new String(buffer, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从raw目录读取文本
     *
     * @param context 上下文
     * @param rawId   id值
     * @return 文本内容
     */
    public String readStringFromRaw(Context context, int rawId) {
        String result = null;
        byte[] buffer = readBytesFromRaw(context, rawId);
        try {
            result = new String(buffer, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获得字符串
     *
     * @param context 上下文
     * @param strId   字符串id
     * @return 字符串
     */
    public String getString(Context context, int strId) {
        return context.getResources().getString(strId);
    }

    /**
     * 获得颜色
     *
     * @param context 上下文
     * @param colorId 颜色id
     * @return 颜色
     */
    public int getColor(Context context, int colorId) {
        return context.getResources().getColor(colorId);
    }

    /**
     * 获得Drawable
     *
     * @param context    上下文
     * @param drawableId Drawable的id
     * @return Drawable
     */
    public Drawable getDrawable(Context context, int drawableId) {
        return context.getResources().getDrawable(drawableId);
    }
}
