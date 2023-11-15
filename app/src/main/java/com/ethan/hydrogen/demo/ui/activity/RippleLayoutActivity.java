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


import com.ethan.hydrogen.demo.R;
import com.ethan.hydrogen.demo.databinding.ActivityRippleLayoutBinding;
import com.ethan.hydrogen.demo.router.PageRouter;
import com.ethan.hydrogen.demo.base.BaseActivity;

import zlc.season.butterfly.annotation.Agile;

@Agile(scheme = PageRouter.ACTIVITY_RIPPLE_LAYOUT)
public class RippleLayoutActivity extends BaseActivity {
    private ActivityRippleLayoutBinding mBinding;
    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_ripple_layout;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding= ActivityRippleLayoutBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    public void initView() {
        mBinding.includeRippleToolbar.tvToolbarTitle.setText(getResources().getString(R.string.activity_ripple_layout));
        setSupportActionBar(mBinding.includeRippleToolbar.toolbar);
        mBinding.includeRippleToolbar.toolbar.setNavigationIcon(com.ethan.hydrogen.R.drawable.ic_arrow_back_24dp);
        mBinding.includeRippleToolbar.toolbar.setNavigationOnClickListener(v -> finish());

    }

    @Override
    public void initListener() {

    }
}
