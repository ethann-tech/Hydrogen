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

package com.ethan.hydrogen.demo.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ethan.hydrogen.OnNoDoubleClickListener;
import com.ethan.hydrogen.demo.R;
import com.ethan.hydrogen.demo.databinding.FragmentFindBinding;
import com.ethan.hydrogen.ui.BaseFragment;
import com.ethan.hydrogen.utils.ToastUtil;
import com.ethan.hydrogen.demo.router.PageRouter;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

public class FindFragment extends BaseFragment {
    private static final String TAG = FindFragment.class.getSimpleName();
    private FragmentFindBinding mBinding;
    private String args1;
    private String args2;

    public static FindFragment newInstance(String args1, String args2) {
        Bundle args = new Bundle();
        FindFragment fragment = new FindFragment();
        fragment.args1 = args1;
        fragment.args2 = args2;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_find;
    }

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {
        mBinding = FragmentFindBinding.inflate(inflater);
        return mBinding.getRoot();
    }

    @Override
    protected void initView(View view) {
        mBinding.btnTest.setText("测试TestGridView");
    }

    @Override
    protected void initListener() {
        super.initListener();
        mBinding.btnTest.setOnClickListener(v -> ARouter.getInstance().build(PageRouter.TEST_GRID_VIEW).navigation());
        mBinding.btnCustomLoading.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                ToastUtil.getInstance().show(getContext(), "OnNoDoubleClickListener");
            }
        });
        mBinding.btnProxy.setOnClickListener(v -> ToastUtil.getInstance().show(getContext(), "是否启用网络代理：" + (isWifiProxy() ? "yes" : "false") + "  ; \n是否启用VPN : " + (isVpnUsed() ? "yes" : "false")));
        mBinding.btnImgCompress1.setOnClickListener(v -> ARouter.getInstance().build(PageRouter.ACTIVITY_IMG_COMPRESS).navigation(getContext()));
    }

    private boolean isWifiProxy() {
        final boolean is_ics_or_later = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        String proxyAddress;
        int proxyPort;
        if (is_ics_or_later) {
            proxyAddress = System.getProperty("http.proxyHost");
            String portstr = System.getProperty("http.proxyPort");
            proxyPort = Integer.parseInt((portstr != null ? portstr : "-1"));
            System.out.println(proxyAddress + "~");
            System.out.println("port = " + proxyPort);
        } else {
            proxyAddress = android.net.Proxy.getHost(getContext());
            proxyPort = android.net.Proxy.getPort(getContext());
            Log.e("address = ", proxyAddress + "~");
            Log.e("port = ", proxyPort + "~");
        }
        return (!TextUtils.isEmpty(proxyAddress)) && (proxyPort != -1);
    }

    public static boolean isVpnUsed() {
        try {
            Enumeration<NetworkInterface> niList = NetworkInterface.getNetworkInterfaces();
            if (niList != null) {
                for (NetworkInterface intf : Collections.list(niList)) {
                    if (!intf.isUp() || intf.getInterfaceAddresses().size() == 0) {
                        continue;
                    }
                    Log.d(TAG, "isVpnUsed() NetworkInterface Name: " + intf.getName());
                    if ("tun0".equals(intf.getName()) || "ppp0".equals(intf.getName())) {
                        return true; // The VPN is up
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }

}
