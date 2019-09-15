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

package com.wonium.hydrogen.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import androidx.annotation.Keep;

/**
 * @ClassName: Md5Util
 * @Description: MD5 工具
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/11/15 9:16
 * @UpdateUser: 添加更新者
 * @UpdateDate: 2018/11/15 9:16
 * @UpdateDescription: 更新描述
 * @Version: 1.0.0
 */
@Keep
public class Md5Util {
    /**
     * 实例对象
     */
    private static Md5Util mInstance;
    public static Md5Util getInstance(){
        if (mInstance==null){
            synchronized (Md5Util.class){
                if (mInstance==null){
                    mInstance =new Md5Util();
                }
            }
        }
        return mInstance;
    }

    /**
     * 字符串MD5加密
     * @param text 被加密的字符串
     * @return 加密后的字符串
     */
    public String getMD5String(String text) {
        byte[] bytes;
        try {
            bytes = MessageDigest.getInstance("md5").digest(text.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("not found this md5 method！");
        }
        return new BigInteger(1, bytes).toString(16);
    }

    /**
     * 获得md5字符串
     * @param text 未被加密的字符串
     * @return 加密后的字符串
     */
    public String get32Md5String(String text){
        StringBuilder builder =new StringBuilder(getMD5String(text));
        // 如果md5code 长度不足32位，则在签名补零 补齐32位
        for (int i = 0; i < 32 - builder.length(); i++) {
            builder.insert(0, "0");
        }
        return builder.toString();
    }

    /**
     * 获得字符串的md5大写值
     * @param text 待加密的字符串
     * @return md5加密后的大写字符串
     */
    public  String getMD5UpperString(String text) {
        return getMD5String(text).toUpperCase();
    }


    /**
     * 获得文件的md5值
     *
     * @param file 文件对象
     * @return 文件的md5
     */
    public  String getFileMD5String(File file) {
        String ret = "";
        FileInputStream in = null;
        FileChannel ch = null;
        try {
            in = new FileInputStream(file);
            ch = in.getChannel();
            ByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
                    file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            ret =new BigInteger(1,md5.digest()).toString(16);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ch != null) {
                try {
                    ch.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    /**
     * 获得文件md5值大写字符串
     *
     * @param file 文件对象
     * @return 文件md5大写字符串
     */
    public  String getFileMD5UpperString(File file) {
        return getFileMD5String(file).toUpperCase();
    }

    /**
     * 校验文件的md5值
     *
     * @param file 目标文件
     * @param md5  基准md5
     * @return 校验结果
     */
    public  boolean checkFileMD5(File file, String md5) {
        return getFileMD5String(file).equalsIgnoreCase(md5);
    }

    /**
     * 校验字符串的md5值
     *
     * @param text 目标字符串
     * @param md5 基准md5
     * @return 校验结果
     */
    public  boolean checkMD5(String text, String md5) {
        return getMD5String(text).equalsIgnoreCase(md5);
    }
}
