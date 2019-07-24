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

package com.wonium.hydrogen.ui.widget;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.wonium.hydrogen.R;

/**
 * @ Class Describe ：
 * @ Created by Gordo on 2016/3/24.
 * @ Modefy by Gordo on 2016/3/24
 */
public class LoadingDialog extends Dialog {
    private TextView tips_loading_msg;
    private String message = null;
    private String msg_load_ing = "加载中";

    public LoadingDialog(Context context) {
        super(context);
        message = msg_load_ing;
    }

    public LoadingDialog(Context context, String message) {
        super(context);
        this.message = message;
        this.setCancelable(false);

    }

    public LoadingDialog(Context context, int themeResId, String message) {
        super(context, themeResId);
        this.setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.view_loading_dialog);
        tips_loading_msg = findViewById(R.id.tips_loading_msg);
        tips_loading_msg.setText(this.message);
    }

    /**
     * 设置数据
     *
     * @param message
     */
    public void setText(CharSequence message) {
        this.message = (String) message;
        tips_loading_msg.setText(this.message);
    }

    /**
     * 设置数据
     *
     * @param resId
     */
    public void setText(int resId) {
        setText(getContext().getResources().getString(resId));
    }

    public void setTextVisibility(int visibility) {

        tips_loading_msg.setVisibility(visibility);
    }
}