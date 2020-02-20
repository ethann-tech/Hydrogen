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

package com.wonium.cicada;


import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @ClassName: App
 * @Description:
 * @uthor: Ethan
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2019/1/19 14:56
 * @UpdateUser: update user
 * @UpdateDate: 2018/11/29 22:56
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
public class App extends Application {
    private static App mInstance;
    private Logger logger=LoggerFactory.getLogger(this.getClass());

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initARouter();
        initLogger();
        QMUISwipeBackActivityManager.init(this);
    }

    public static App getInstance() {
        return mInstance;
    }

    /**
     * 初始化页面路由
     */
    private void initARouter() {
        logger.debug("initARouter: " + BuildConfig.DEBUG);
        logger.debug("LOG:App:initARouter init={}", "hydrogen");
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
            ARouter.printStackTrace();
        }
        ARouter.init(this);

    }

    /**
     * 初始化Logger
     */
    private void initLogger() {
        logger = LoggerFactory.getLogger(this.getClass());
        logger.error("LOG:Application:onCreate =================================================================================");
        logger.debug("LOG:Application:onCreate content=debug !!! this log should remove !!!");
        logger.trace("LOG:Application:onCreate content=trace !!! this log should remove !!!");
        logger.info("LOG:Application:onCreate content=info  !!! this log should remove !!!");
        logger.warn("LOG:Application:onCreate content=warn  !!! this log should remove !!!");
        logger.error("LOG:Application:onCreate content=error !!! this log should remove !!!");
        logger.info("LOG:Application:onCreate apk build time={}", BuildConfig.BUILD_TIME);
        logger.error("LOG:Application:onCreate =================================================================================");
    }
}
