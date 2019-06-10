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

package com.wonium.aclj.ui.activity;

import android.Manifest;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivityDeviceUtilBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.cicada.ui.BaseActivity;
import com.wonium.cicada.utils.DeviceUtil;
import com.wonium.cicada.utils.NetWorkUtil;
import com.wonium.cicada.utils.StringUtil;
import com.wonium.cicada.utils.ToastUtil;

import ru.alexbykov.nopermission.PermissionHelper;

/**
 * @ClassName: DeviceActivity.java
 * @Description: 设备信息Activity
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/11/19 23:31
 * @UpdateUser: update user
 * @UpdateDate: 2018/11/19 23:31
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
@Route(path = PageRouter.ACTIVITY_DEVICE_UTIL)
public class DeviceUtilActivity extends BaseActivity {
    private ActivityDeviceUtilBinding mBinding;
    private PermissionHelper mPermissionHelper;

    @Override
    protected int getStatusColor() {
        return getContext().getResources().getColor(R.color.black);
    }

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
        setScreenRoate(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_device_util;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = DataBindingUtil.setContentView(this, layoutResId);
    }

    @Override
    public void initView() {
        setSupportActionBar(mBinding.includeLayoutDeviceToolbar.toolbar);
        mBinding.setTitle(getResources().getString(R.string.tools_device));
        mBinding.includeLayoutDeviceToolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
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
                .append(DeviceUtil.getInstance().getVersionName(this))
                .append("\n软件版本号-->").append(StringUtil.getInstance().valueOf(DeviceUtil.getInstance().getVersionCode(this)))
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
