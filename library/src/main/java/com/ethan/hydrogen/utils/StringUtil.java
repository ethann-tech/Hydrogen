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

import android.text.TextUtils;

import androidx.annotation.Keep;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ethan
 */
@Keep
public class StringUtil {
    /**
     * 实例对象
     */
    private StringUtil() {
    }

    private static class Inner {
        private static final StringUtil INSTANCE = new StringUtil();
    }


    public static StringUtil getInstance() {
        return Inner.INSTANCE;
    }

    /**
     * 判空操作 如果为null 返回true 否则返回false
     *
     * @param text 被判断字符串
     * @return boolean  true 为空，false 不为空
     */
    public boolean isNull(CharSequence text) {
        return TextUtils.isEmpty(text);
    }

    /**
     * 字符串判空  如果为空则返回 “”空字符串，如果不为空则返回原字符串
     *
     * @param text 被判断的字符串
     * @return String
     */
    public String isEmpty(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        } else {
            return text;
        }
    }

    /**
     * 将任意类型对象转换成字符串
     *
     * @param value 被转换对象
     * @param <T>   泛型对象
     * @return String  被转换对象转换后的字符串
     */
    public <T> String valueOf(T value) {
        if (value == null) {
            return "";
        }
        return String.valueOf(value);
    }

    /**
     * 从Map集合中获取给定Key对应的Value
     *
     * @param map          字符串集合
     * @param key          map 中的key
     * @param defaultValue 默认值
     * @return String  key 对应的值
     */
    public String getStringFromMap(Map map, String key, String defaultValue) {
        Object obj = map.get(key);
        return obj == null ? defaultValue : (obj instanceof Number && Pattern.matches("^[-\\+]?[\\d]*\\.0*$", obj.toString()) ? String.valueOf(
                Long.valueOf(((Number) obj).longValue())) : obj.toString());
    }

    /**
     * 反转字符串 例如  wonium ->muinow
     *
     * @param charset 需反转的字符串
     * @return 字符串
     */
    public String reverseString(String charset) {
        return new StringBuffer(charset).reverse().toString();
    }


    /**
     * 格式化整数 小于10的整数前面补零，大于等于10的整数不变。
     *
     * @param value 整数
     * @return 字符型数字
     */
    public String formatInt(int value) {
        if (value < 10) {
            return "0" + value;
        } else {
            return String.valueOf(value);
        }
    }

    /**
     * 更改字符集
     *
     * @param str        字符串
     * @param newCharset 如 utf-8
     * @return 字符转码成功返回新的字符串，否则返回null
     * @throws UnsupportedEncodingException
     */
    public String changeCharSet(String str, String newCharset) throws UnsupportedEncodingException {
        if (str != null) {
            // 用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            // 用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }

    /**
     * 检测是否有emoji表情
     *
     * @param source
     * @return
     */
    public boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            //如果不能匹配,则该字符是Emoji表情
            if (isEmojiCharacter(codePoint)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEmojiCharacter(char codePoint) {
        return !(codePoint == 0x0 || codePoint == 0x9 || codePoint == 0xA || codePoint == 0xD || codePoint >= 0x20 && codePoint <= 0xD7FF);

    }

    /**
     * 判断是否含有特殊字符
     *
     * @param str
     * @return true为包含，false为不包含
     */
    public boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

}
