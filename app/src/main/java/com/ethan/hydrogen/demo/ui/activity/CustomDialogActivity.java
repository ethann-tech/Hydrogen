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

package com.ethan.hydrogen.demo.ui.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ethan.hydrogen.demo.databinding.ActivityCustomDialogBinding;
import com.ethan.hydrogen.ui.widget.LoadingDialog;
import com.ethan.hydrogen.demo.R;
import com.ethan.hydrogen.demo.router.PageRouter;
import com.ethan.hydrogen.demo.base.BaseActivity;

/**
 * @author fxhhq
 */
@Route(path = PageRouter.ACTIVITY_CUSTOM_DIALOG)
public class CustomDialogActivity extends BaseActivity {
    private ActivityCustomDialogBinding mBinding;

    @Override
    protected int getStatusColor() {
        return getResources().getColor(com.ethan.hydrogen.R.color.black);
    }

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_custom_dialog;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = ActivityCustomDialogBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    public void initView() {
        setStatusBar(true);
        setSupportActionBar(mBinding.includeCustomDialogToolbar.toolbar);
        mBinding.includeCustomDialogToolbar.tvToolbarTitle.setText(getResources().getString(R.string.activity_custom_dialog));
        mBinding.includeCustomDialogToolbar.toolbar.setNavigationOnClickListener(v -> finish());
        mBinding.includeCustomDialogToolbar.toolbar.setNavigationIcon(com.ethan.hydrogen.R.drawable.ic_arrow_back_24dp);
    }

    @Override
    public void initListener() {
        mBinding.btnLoadingDialog.setOnClickListener(v -> {
            LoadingDialog dialog = new LoadingDialog(CustomDialogActivity.this);
            dialog.show();
        });
    }
}
