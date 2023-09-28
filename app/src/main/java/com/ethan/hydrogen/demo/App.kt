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
package com.ethan.hydrogen.demo

import com.alibaba.android.arouter.BuildConfig
import com.alibaba.android.arouter.launcher.ARouter
import com.ethan.hydrogen.demo.common.ApplicationLifecycle
import com.ethan.hydrogen.demo.common.log.ImplLoggerManager
import com.ethan.hydrogen.utils.DateUtil
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager
import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import java.util.Date
import kotlin.coroutines.coroutineContext

class App : ApplicationLifecycle() {
    private val mLoggerManager by lazy { ImplLoggerManager(mContext = this.baseContext) }
    private val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)
    override suspend fun onCreatedBySuspend() {
        super.onCreatedBySuspend()
        instance = this
        initARouter()
        initLogger()
        QMUISwipeBackActivityManager.init(this)
    }


    /**
     * 初始化页面路由
     */
    private fun initARouter() {

        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
            ARouter.printStackTrace()
        }
        ARouter.init(this)
    }

    /**
     * 初始化Logger
     */
    private suspend fun initLogger() {
        mLoggerManager.init()
        mLogger.debug("LOG:ApplicationTest:onCreate 1={}", 1)
        mLogger.debug("LOG:ApplicationTest:onCreate coroutineContext={}", coroutineContext)
        mLogger.error("LOG:Application:onCreate =================================================================================")
        mLogger.debug("LOG:Application:onCreate content=debug !!! this log should remove !!!")
        mLogger.trace("LOG:Application:onCreate content=trace !!! this log should remove !!!")
        mLogger.info("LOG:Application:onCreate content=info  !!! this log should remove !!!")
        mLogger.warn("LOG:Application:onCreate content=warn  !!! this log should remove !!!")
        mLogger.error("LOG:Application:onCreate content=error !!! this log should remove !!!")
        mLogger.info("LOG:Application:onCreate apk build time={}", DateUtil.getInstance().formatDate(Date(), DateUtil.DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MIN_SECOND))
        mLogger.error("LOG:Application:onCreate =================================================================================")
    }

    companion object {
        var instance: App? = null
            private set
    }
}