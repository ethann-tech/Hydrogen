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
import com.ethan.hydrogen.demo.databinding.ActivityDataCleanBinding;
import com.ethan.hydrogen.demo.R;
import com.ethan.hydrogen.demo.router.PageRouter;
import com.ethan.hydrogen.demo.base.BaseActivity;
import com.ethan.hydrogen.utils.DataCleanUtil;
import com.ethan.hydrogen.utils.StringUtil;

/**
 * @ClassName: AcacheActivity.java
 * @Description: 类描述
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/11/17 15:25
 * @UpdateUser: update user
 * @UpdateDate: 2018/11/17 15:25
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
@Route(path = PageRouter.ACTIVITY_DATA_CLEAR)
public class DataCleanActivity extends BaseActivity {
    private ActivityDataCleanBinding binding;

    @Override
    public void initListener() {
        binding.btnGetAcache.setOnClickListener(v -> {
            try {
                binding.tvAcacheResult.setText(StringUtil.getInstance().isEmpty(DataCleanUtil.getInstance().getTotalCacheSize(this)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        binding.btnCleanAcache.setOnClickListener(v -> binding.tvAcacheResult.setText(StringUtil.getInstance().isEmpty(DataCleanUtil.getInstance().clearAllCache(this) + "KB")));
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
        return R.layout.activity_data_clean;
    }

    @Override
    public void bindLayout(int layoutResId) {
        binding = ActivityDataCleanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public void initView() {
        setSupportActionBar(binding.includeLayoutAcacheToolbar.toolbar);
        binding.includeLayoutAcacheToolbar.toolbar.setNavigationIcon(com.ethan.hydrogen.R.drawable.ic_arrow_back_24dp);
        binding.includeLayoutAcacheToolbar.toolbar.setNavigationOnClickListener(v -> finish());
        binding.includeLayoutAcacheToolbar.tvToolbarTitle.setText(getString(R.string.tools_data_clean));
    }
}
