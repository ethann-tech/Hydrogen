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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import androidx.annotation.Keep;


/**
 * @ClassName: ShellUtil
 * @Description: Shell 工具类
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/11/25 21:27
 * @UpdateUser: update user
 * @UpdateDate: 2018/11/25 21:27
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
@Keep
public class ShellUtil {
    private ShellUtil() {
        if (Inner.INSTANCE != null) {
            throw new RuntimeException("该实例已存在，请通过getInstance方法获取");
        }
    }

    /**
     * 实例对象
     */
    private static class Inner {
        private static ShellUtil INSTANCE = new ShellUtil();
    }

    public static ShellUtil getInstance() {
        return Inner.INSTANCE;
    }

    private static final String LINE_SEP = System.getProperty("line.separator");


    /**
     * 是否是在 root 下执行命令
     *
     * @param command 命令
     * @param isRoot  是否需要 root 权限执行
     * @return CommandResult
     */
    public CommandResult execCmd(final String command, final boolean isRoot) {
        return execCmd(new String[]{command}, isRoot, true);
    }

    /**
     * 是否是在 root 下执行命令
     *
     * @param commands 多条命令链表
     * @param isRoot   是否需要 root 权限执行
     * @return CommandResult
     */
    public CommandResult execCmd(final List<String> commands, final boolean isRoot) {
        return execCmd(commands == null ? null : commands.toArray(new String[]{}), isRoot, true);
    }

    /**
     * 是否是在 root 下执行命令
     *
     * @param commands 多条命令数组
     * @param isRoot   是否需要 root 权限执行
     * @return CommandResult
     */
    public CommandResult execCmd(final String[] commands, final boolean isRoot) {
        return execCmd(commands, isRoot, true);
    }

    /**
     * 是否是在 root 下执行命令
     *
     * @param command         命令
     * @param isRoot          是否需要 root 权限执行
     * @param isNeedResultMsg 是否需要结果消息
     * @return CommandResult
     */
    public CommandResult execCmd(final String command, final boolean isRoot, final boolean isNeedResultMsg) {
        return execCmd(new String[]{command}, isRoot, isNeedResultMsg);
    }

    /**
     * 是否是在 root 下执行命令
     *
     * @param commands        命令链表
     * @param isRoot          是否需要 root 权限执行
     * @param isNeedResultMsg 是否需要结果消息
     * @return CommandResult
     */
    public CommandResult execCmd(final List<String> commands, final boolean isRoot, final boolean isNeedResultMsg) {
        return execCmd(commands == null ? null : commands.toArray(new String[]{}), isRoot, isNeedResultMsg);
    }

    /**
     * 是否是在 root 下执行命令
     *
     * @param commands        命令数组
     * @param isRoot          是否需要 root 权限执行
     * @param isNeedResultMsg 是否需要结果消息
     * @return CommandResult
     */
    public CommandResult execCmd(final String[] commands, final boolean isRoot, final boolean isNeedResultMsg) {
        int result = -1;
        if (commands == null || commands.length == 0) {
            return new CommandResult(result, null, null);
        }
        Process process = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = null;
        StringBuilder errorMsg = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec(isRoot ? "su" : "sh");
            os = new DataOutputStream(process.getOutputStream());
            for (String command : commands) {
                if (command == null) {
                    continue;
                }
                os.write(command.getBytes());
                os.writeBytes(LINE_SEP);
                os.flush();
            }
            os.writeBytes("exit" + LINE_SEP);
            os.flush();
            result = process.waitFor();
            if (isNeedResultMsg) {
                successMsg = new StringBuilder();
                errorMsg = new StringBuilder();
                successResult = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
                errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
                String line;
                if ((line = successResult.readLine()) != null) {
                    successMsg.append(line);
                    while ((line = successResult.readLine()) != null) {
                        successMsg.append(LINE_SEP).append(line);
                    }
                }
                if ((line = errorResult.readLine()) != null) {
                    errorMsg.append(line);
                    while ((line = errorResult.readLine()) != null) {
                        errorMsg.append(LINE_SEP).append(line);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseUtil.getInstance().closeIO(os, successResult, errorResult);
            if (process != null) {
                process.destroy();
            }
        }
        return new CommandResult(result, successMsg == null ? null : successMsg.toString(), errorMsg == null ? null : errorMsg.toString());
    }

    /**
     * 返回的命令结果
     */
    public class CommandResult {
        /**
         * 结果码
         **/
        public int result;
        /**
         * 成功信息
         **/
        public String successMsg;
        /**
         * 错误信息
         **/
        public String errorMsg;

        public CommandResult(final int result, final String successMsg, final String errorMsg) {
            this.result = result;
            this.successMsg = successMsg;
            this.errorMsg = errorMsg;
        }
    }
}
