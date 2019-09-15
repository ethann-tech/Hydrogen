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

package com.wonium.cicada.ui.activity;

import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.cicada.R;
import com.wonium.cicada.databinding.ActivityImageViewBinding;
import com.wonium.cicada.router.PageRouter;
import com.wonium.hydrogen.ui.BaseActivity;

/**
 * @ClassName: ImageViewActivity
 * @Description:
 * @author: dahai
 * @E-mail:
 * @Blog:
 * @CreateDate: 2019/6/3 17:46
 * @UpdateUser: dahai
 * @UpdateDate: 2019/6/3 17:46
 * @UpdateDescription:
 * @Version:
 */
@Route(path = PageRouter.ACTIVITY_IMAGE_VIEW)
public class ImageViewActivity extends BaseActivity {
    private ActivityImageViewBinding mBinding;

    @Override
    protected int getStatusColor() {
        return getResources().getColor(R.color.black);
    }

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_image_view;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = DataBindingUtil.setContentView(this, layoutResId);
    }

    @Override
    public void initView() {
        setStatusBar(true);
        setSupportActionBar(mBinding.includeImageViewToolbar.toolbar);
        mBinding.setTitle(getResources().getString(R.string.activity_image_view));
        mBinding.includeImageViewToolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
    }

    @Override
    public void initListener() {
        mBinding.includeImageViewToolbar.toolbar.setNavigationOnClickListener(v -> finish());
    }
}
