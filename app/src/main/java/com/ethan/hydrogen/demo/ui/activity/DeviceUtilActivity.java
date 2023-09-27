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

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ethan.hydrogen.demo.R;
import com.ethan.hydrogen.demo.databinding.ActivityDeviceUtilBinding;
import com.ethan.hydrogen.demo.router.PageRouter;
import com.ethan.hydrogen.demo.base.BaseActivity;
import com.ethan.hydrogen.utils.DeviceUtil;
import com.ethan.hydrogen.utils.NetWorkUtil;
import com.ethan.hydrogen.utils.PackageInfoUtil;
import com.ethan.hydrogen.utils.StringUtil;
import com.ethan.hydrogen.utils.ToastUtil;

import ru.alexbykov.nopermission.PermissionHelper;

/**
 * @author Ethan
 */

@Route(path = PageRouter.ACTIVITY_DEVICE_UTIL)
public class DeviceUtilActivity extends BaseActivity {
    private ActivityDeviceUtilBinding mBinding;
    private PermissionHelper mPermissionHelper;

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
        return R.layout.activity_device_util;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = ActivityDeviceUtilBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    public void initView() {
        setSupportActionBar(mBinding.includeLayoutDeviceToolbar.toolbar);
        mBinding.includeLayoutDeviceToolbar.tvToolbarTitle.setText(getResources().getString(R.string.tools_device));
        mBinding.includeLayoutDeviceToolbar.toolbar.setNavigationIcon(com.ethan.hydrogen.R.drawable.ic_arrow_back_24dp);
        mPermissionHelper = new PermissionHelper(this);
    }

    @Override
    public void initListener() {
        mBinding.includeLayoutDeviceToolbar.toolbar.setNavigationOnClickListener(v -> finish());
        mBinding.btnDeviceInfo.setOnClickListener(v -> getDeviceInfo());
    }

    private void getDeviceInfo() {

        mPermissionHelper.check(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_SMS)
                .onSuccess(this::onSuccess).onDenied(this::onDenied).onNeverAskAgain(this::onNeverAskAgain).run();

    }

    private void onSuccess() {
        StringBuilder builder = new StringBuilder();
        builder.append("当前软件版本-->")
                .append(PackageInfoUtil.getInstance().getVersionCode(this))
                .append("\n软件版本号-->").append(StringUtil.getInstance().valueOf(PackageInfoUtil.getInstance().getVersionCode(this)))
                .append("\nphone number-->")
                .append(StringUtil.getInstance().isEmpty(DeviceUtil.getInstance().getPhoneNumber(getContext())))
                .append("\nIMEI-->")
                .append(DeviceUtil.getInstance().getDeviceIMEI(this))
                .append("\nmac addr-->")
                .append(DeviceUtil.getInstance().getMacAddress(this))
                .append("\nbrand-->")
                .append(StringUtil.getInstance().isEmpty(DeviceUtil.getInstance().getBrand()))
                .append("\n手机型号-->")
                .append(DeviceUtil.getInstance().getModel())
                .append("\nAndroid_ID-->")
                .append(DeviceUtil.getInstance().getAndroidId(this))
                .append("\nWIFI SSID-->").append(NetWorkUtil.getInstance().getWifiSsid(this));
        mBinding.tvDeviceResult.setText(builder);
    }

    private void onDenied() {
        ToastUtil.getInstance().show(this, "用户拒绝");
    }

    private void onNeverAskAgain() {
        ToastUtil.getInstance().show(this, "onNeverAskAgain");
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
