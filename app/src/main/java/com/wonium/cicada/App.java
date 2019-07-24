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
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.wonium.cicada.BuildConfig;
import com.wonium.cicada.R;


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
    private String TAG=this.getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initARouter();
        initLogger();
    }

    public static App getInstance() {
        return mInstance;
    }

    /**
     * 初始化页面路由
     */
    private void initARouter(){
        Log.d(TAG, "initARouter: "+String.valueOf(BuildConfig.DEBUG));
        // These two lines must be written before init, otherwise these configurations will be invalid in the init process
        if (BuildConfig.DEBUG) {
            // Print log
            ARouter.openLog();
            // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
            ARouter.openDebug();
            ARouter.printStackTrace();
        }
        ARouter.init(this);

    }

    /**
     * 初始化Logger
     */
    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder().showThreadInfo(true).methodCount(5).methodOffset(7).tag(getBaseContext().getResources().getString(R.string.app_name)).build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }


}
