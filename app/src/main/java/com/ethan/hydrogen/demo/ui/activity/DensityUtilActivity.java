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
import com.ethan.hydrogen.demo.R;
import com.ethan.hydrogen.demo.databinding.ActivityDensityUtilBinding;

import com.ethan.hydrogen.demo.router.PageRouter;
import com.ethan.hydrogen.demo.base.BaseActivity;
import com.ethan.hydrogen.utils.DensityUtil;

/**
 * @author fxhhq
 */
@Route(path = PageRouter.ACTIVITY_DENSITY_UTIL)
public class DensityUtilActivity extends BaseActivity {
    private ActivityDensityUtilBinding mBinding;

    @Override
    protected int getStatusColor() {
        return getResources().getColor(com.ethan.hydrogen.R.color.black);
    }

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
        setScreenRotate(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_density_util;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = ActivityDensityUtilBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    public void initView() {
        mBinding.includeDensityUtilToolbar.tvToolbarTitle.setText(getResources().getString( R.string.tools_density));
        setSupportActionBar(mBinding.includeDensityUtilToolbar.toolbar);
        mBinding.includeDensityUtilToolbar.toolbar.setNavigationIcon(
                com.ethan.hydrogen.R.drawable.ic_arrow_back_24dp);
        StringBuilder builder = new StringBuilder();
        builder.append("DensityUtil是一个屏幕信息获取数值的转换类\n")
                .append("使用示例：\n")
                .append(" DensityUtil.getInstance().dip2px(getContext(),10)-->")
                .append(DensityUtil.getInstance().dip2px(getContext(), 10));
        mBinding.tv.setText(builder.toString());
    }

    @Override
    public void initListener() {
        mBinding.includeDensityUtilToolbar.toolbar.setNavigationOnClickListener(v -> finish());
    }
}
