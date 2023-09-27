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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Keep;

import static java.util.regex.Pattern.*;



@Keep
public class VerifyUtil {
    /**
     * 实例对象
     */
    private VerifyUtil() {
        if (Inner.INSTANCE != null) {
            throw new RuntimeException("该实例已存在，请通过getInstance方法获取");
        }
    }

    private static class Inner {
        private static final VerifyUtil INSTANCE = new VerifyUtil();
    }

    public static VerifyUtil getInstance() {
        return Inner.INSTANCE;
    }

    /**
     * 验证密码
     *
     * @param password 密码
     * @return 以字母开头，长度在6-18之间，只能包含字符、数字和下划线
     */
    public boolean checkPasswrd(String password) {

        return matches("^[a-zA-Z]\\w{5,19}$", password);
    }

    /**
     * 验证手机号
     *
     * @param mobiles 手机号
     * @return
     */
    public boolean isMobileNO(String mobiles) {
        return matches("^((13[0-9])|(15[^4,\\D])|(16[0-9])|(17[0-9])|(18[0-9]))\\d{8}$", mobiles);
    }


    /**
     * 手机号码，中间4位星号替换
     *
     * @param phone 手机号
     * @return 中间四位数被星号替换的手机号
     */
    public String phoneNoHide(String phone) {
        // 括号表示组，被替换的部分$n表示第n组的内容
        // 正则表达式中，替换字符串，括号的意思是分组，在replace()方法中，
        // 参数二中可以使用$n(n为数字)来依次引用模式串中用括号定义的字串。
        // "(\d{3})\d{4}(\d{4})", "$1****$2"的这个意思就是用括号，
        // 分为(前3个数字)中间4个数字(最后4个数字)替换为(第一组数值，保持不变$1)(中间为*)(第二组数值，保持不变$2)
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 验证身份证号码
     *
     * @param idCard 居民身份证号码15位或18位，最后一位可能是数字或字母
     * @return 验证成功返回true，验证失败返回false
     */
    public boolean checkIdCard(String idCard) {
        String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
        return matches(regex, idCard);
    }


    /**
     * 短信验证码长度校验 6位
     *
     * @param smsCode 短信验证码
     * @return 合法返回true 不合法返回false
     */
    public boolean isSmsCode(String smsCode) {
        return matches("\\d{6}", smsCode);
    }


    /**
     * 校验银行卡卡号是否有效
     *
     * @param cardId 卡号
     * @return 是否有效卡号 false 无效，true 有效
     */
    public boolean checkBankCard(String cardId) {
        char isBit = 'N';
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == isBit) {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId 不含校验位的卡号
     * @return 校验结果
     */
    private char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0 || !nonCheckCodeCardId.matches("\\d+")) {
            // 如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int sum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            sum += k;
        }
        return (sum % 10 == 0) ? '0' : (char) ((10 - sum % 10) + '0');
    }

    /**
     * 验证整数（正整数和负整数）
     *
     * @param digit 一位或多位0-9之间的整数
     * @return 验证成功返回true，验证失败返回false
     */
    public boolean checkDigit(String digit) {
        String regex = "\\-?[1-9]\\d+";
        return matches(regex, digit);
    }

    /**
     * 验证整数和浮点数（正负整数和正负浮点数）
     *
     * @param decimals 一位或多位0-9之间的浮点数，如：1.23，233.30
     * @return 验证成功返回true，验证失败返回false
     */
    public boolean checkDecimals(String decimals) {
        String regex = "\\-?[1-9]\\d+(\\.\\d+)?";
        return matches(regex, decimals);
    }


    /**
     * 验证空白字符
     *
     * @param blankSpace 空白字符，包括：空格、\t、\n、\r、\f、\x0B
     * @return 验证成功返回true，验证失败返回false
     */
    public boolean checkBlankSpace(String blankSpace) {
        String regex = "\\s+";
        return matches(regex, blankSpace);
    }

    /**
     * 验证中文
     *
     * @param chinese 中文字符
     * @return 验证成功返回true，验证失败返回false
     */
    public boolean checkChinese(String chinese) {
        String regex = "^[\u4E00-\u9FA5]+$";
        return matches(regex, chinese);
    }

    /**
     * 验证日期（年月日）
     *
     * @param birthday 日期，格式：1992-09-03，或1992.09.03
     * @return 验证成功返回true，验证失败返回false
     */
    public boolean checkBirthday(String birthday) {
        String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";
        return matches(regex, birthday);
    }

    /**
     * 验证URL地址
     *
     * @param url 格式：https://blog.wonium.com/archives/70/ 或 http://www.csdn.net:80
     * @return 验证成功返回true，验证失败返回false
     */
    public boolean checkURL(String url) {
        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
        return matches(regex, url);
    }

    /**
     * 匹配中国邮政编码
     *
     * @param postcode 邮政编码
     * @return 验证成功返回true，验证失败返回false
     */
    public boolean checkPostcode(String postcode) {
        String regex = "[1-9]\\d{5}";
        return matches(regex, postcode);
    }

    /**
     * 匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
     *
     * @param ipAddress IPv4标准地址
     * @return 验证成功返回true，验证失败返回false
     */
    public boolean checkIpAddress(String ipAddress) {
        String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";
        return matches(regex, ipAddress);
    }


    /**
     * 判断是否为纯字母或数字
     *
     * @param str 待验证的字符串
     * @return 是否为纯字母或数字
     */
    public boolean isAlphaBetaOrNumbericString(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        // 从开头到结尾必须全部为字母或者数字
        Pattern p = compile("^[a-zA-Z0-9]+$");
        Matcher m = p.matcher(str);

        return m.find();
    }


    /**
     * 判断字符串是否为连续字母 xyZaBcd等
     *
     * @param str 待验证的字符串
     * @return 是否为连续字母
     */
    public boolean isContinuousWord(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!isAlphaBetaString(str)) {
            return true;
        }
        int len = str.length();
        String local = str.toLowerCase();
        for (int i = 0; i < len - 1; i++) {
            char curChar = local.charAt(i);
            char verifyChar = (char) (curChar + 1);
            if (curChar == 'z') {
                verifyChar = 'a';
            }
            char nextChar = local.charAt(i + 1);
            if (nextChar != verifyChar) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否是纯字母
     *
     * @param str 待验证的字符串
     * @return 是否是纯字母
     */
    public boolean isAlphaBetaString(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        // 从开头到结尾必须全部为字母或者数字
        Pattern p = compile("^[a-zA-Z]+$");
        Matcher m = p.matcher(str);

        return m.find();
    }

    /**
     * 是否是合法的邮箱
     *
     * @param str 待验证的字符串
     * @return 是否是合法的邮箱
     */
    public boolean isValidEmail(String str) {
        Pattern pattern = compile("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?");
        Matcher isValid = pattern.matcher(str);
        return isValid.matches();

    }

    /**
     * 判断字符串是否为连续数字 45678901等
     *
     * @param str 待验证的字符串
     * @return 是否为连续数字
     */
    public boolean isContinuousNum(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!isNumbericString(str)) {
            return true;
        }
        int len = str.length();
        for (int i = 0; i < len - 1; i++) {
            char curChar = str.charAt(i);
            char verifyChar = (char) (curChar + 1);
            if (curChar == '9') {
                verifyChar = '0';
            }
            char nextChar = str.charAt(i + 1);
            if (nextChar != verifyChar) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否为纯数字
     *
     * @param str 待验证的字符串
     * @return 是否为纯数字
     */
    public boolean isNumbericString(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        // 从开头到结尾必须全部为数字
        Pattern p = compile("^[0-9]+$");
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 判断是否为重复字符串
     *
     * @param str 待验证的字符串
     * @return 是否为重复字符串
     */
    public boolean isRepeatedString(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int len = str.length();
        if (len <= 1) {
            return false;
        } else {
            // 第一个字符
            char firstChar = str.charAt(0);
            for (int i = 1; i < len; i++) {
                // 第i个字符
                char nextChar = str.charAt(i);
                if (firstChar != nextChar) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 判断是否是英语
     */
    public boolean isEnglish(String charaString) {
        return charaString.matches("^[a-zA-Z]*");
    }

    /**
     * 是否含有特殊符号
     *
     * @param str 待验证的字符串
     * @return 是否含有特殊符号
     */
    public boolean hasSpecialCharacter(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 是否包含数字
     *
     * @param str 待验证的字符串
     * @return 是否含数字
     */
    public boolean hasNumeric(String str) {
        Pattern pattern = compile("[0-9]+");
        return pattern.matcher(str).find();
    }


}
