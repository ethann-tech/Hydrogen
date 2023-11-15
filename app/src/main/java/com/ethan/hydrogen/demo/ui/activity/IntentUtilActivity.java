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

import android.Manifest;

import androidx.annotation.NonNull;

import com.ethan.hydrogen.demo.R;
import com.ethan.hydrogen.demo.databinding.ActivityIntentUtilBinding;
import com.ethan.hydrogen.utils.IntentUtil;
import com.ethan.hydrogen.utils.ToastUtil;
import com.ethan.hydrogen.demo.router.PageRouter;
import com.ethan.hydrogen.demo.base.BaseActivity;


import ru.alexbykov.nopermission.PermissionHelper;
import zlc.season.butterfly.annotation.Agile;


@Agile(scheme = PageRouter.ACTIVITY_INTENT_UTIL)
public class IntentUtilActivity extends BaseActivity {
    private ActivityIntentUtilBinding mBinding;
    private PermissionHelper mPermissionHelper;

    @Override
    public void initWindowAttributes() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_intent_util;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = ActivityIntentUtilBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    public void initView() {
        mBinding.includeIntentUtil.tvToolbarTitle.setText(getResources().getString(R.string.tools_intent));
        setSupportActionBar(mBinding.includeIntentUtil.toolbar);
        mBinding.includeIntentUtil.toolbar.setNavigationIcon(com.ethan.hydrogen.R.drawable.ic_arrow_back_24dp);
        mPermissionHelper = new PermissionHelper(this);
    }

    @Override
    public void initListener() {
        mBinding.includeIntentUtil.toolbar.setNavigationOnClickListener(v -> finish());

        mBinding.btnIntentToCallPhone.setOnClickListener(v -> checkPermissionForCallPhone());
    }

    /**
     * 检查拨打电话权限
     */
    private void checkPermissionForCallPhone() {
        mPermissionHelper.check(Manifest.permission.CALL_PHONE).onDenied(() -> ToastUtil.getInstance().show(getContext(), "没有授权，无法打开拨号页面")).onSuccess(() -> IntentUtil.getInstance().callPhone(getContext(), "18751705121")).onNeverAskAgain(() -> ToastUtil.getInstance().show(getContext(), "不再询问")).run();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionHelper.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
