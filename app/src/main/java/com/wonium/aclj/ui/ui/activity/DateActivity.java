/*
 * Copyright  2018.  wonium
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
 *
 */

package com.wonium.aclj.ui.ui.activity;

import android.support.v7.widget.AppCompatButton;


import com.wonium.aclj.R;
import com.wonium.cicada.android.BaseActivity;
import com.wonium.cicada.android.ui.weight.ScheduledButton;
import com.wonium.cicada.android.utils.ToastUtil;

/**
 * @ClassName: DateActivity
 * @Description: 描述一下
 * @Author: Ethan
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2019/1/15 21:46
 * @UpdateUser: update user
 * @UpdateDate: 2019/1/15 21:46
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
public class DateActivity extends BaseActivity {
    private ScheduledButton btnTime;
    private AppCompatButton btnStop;

    @Override
    public void initListener() {
        btnTime.setOnClickListener(v -> {btnTime.start();
        ToastUtil.INSTANCE.show(getContext(),"开始");
        });

        btnStop.setOnClickListener(v -> {
            btnTime.cancel();
            ToastUtil.INSTANCE.show(getContext(),"暂停");
        });

    }

    @Override
    public void initWindowAttributes() {
    setAllowFullScreen(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_date;
    }

    @Override
    public void bindLayout(int layoutResId) {

    }

    @Override
    public void initView() {

        btnTime =findViewById(R.id.btn_time);
        btnStop =findViewById(R.id.btn_stop);
    }
}
