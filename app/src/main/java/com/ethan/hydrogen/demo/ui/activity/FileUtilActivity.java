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

import com.ethan.hydrogen.demo.databinding.ActivityFileUtilBinding;
import com.ethan.hydrogen.demo.router.PageRouter;
import com.ethan.hydrogen.demo.base.BaseActivity;
import com.ethan.hydrogen.utils.FileUtil;
import com.ethan.hydrogen.demo.R;

@Route(path = PageRouter.ACTIVITY_FILE_UTIL)
public class FileUtilActivity extends BaseActivity {

    private ActivityFileUtilBinding mBinding;

    @Override
    public void initListener() {

        mBinding.btnReadFile.setOnClickListener(v -> mBinding.tvFileResult.setText(FileUtil.getInstance().readAssetsFile(this, "wonium.wn")));
        mBinding.includeToolbarFile.toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected int getStatusColor() {
        return getContext().getResources().getColor(com.ethan.hydrogen.R.color.black);
    }

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
        setScreenRotate(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_file_util;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = ActivityFileUtilBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    public void initView() {
        setSupportActionBar(mBinding.includeToolbarFile.toolbar);
        mBinding.includeToolbarFile.tvToolbarTitle.setText(getString(R.string.tools_file));
        mBinding.tvFileResult.setText("点击按钮读取wonium文件");
        mBinding.includeToolbarFile.toolbar.setNavigationIcon(com.ethan.hydrogen.R.drawable.ic_arrow_back_24dp);

    }
}
