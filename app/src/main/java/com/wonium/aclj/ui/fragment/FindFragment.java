package com.wonium.aclj.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.FragmentFindBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.aclj.ui.MainActivity;
import com.wonium.cicada.ui.BaseFragment;
import com.wonium.cicada.utils.ToastUtil;

import androidx.databinding.DataBindingUtil;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;


/**
 * @ClassName: FindFragment
 * @Description: 描述一下
 * @Author: Ethan
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2019/3/27 21:21
 * @UpdateUser: update user
 * @UpdateDate: 2019/3/27 21:21
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
public class FindFragment extends BaseFragment {
    private static final String TAG =FindFragment.class.getSimpleName();
    private FragmentFindBinding mBinding;
    private String args1;
    private String args2;


    public static FindFragment newInstance(String args1,String args2) {
        Bundle args = new Bundle();
        FindFragment fragment = new FindFragment();
        fragment.args1 =args1;
        fragment.args2 =args2;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_find;
    }

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {

        mBinding = DataBindingUtil.inflate(inflater,getLayoutResId(),container,false);
        return mBinding.getRoot();
    }

    @Override
    protected void initView(View view) {
        mBinding.setBtnFind("TestGridView");
    }

    @Override
    protected void initListener() {
        super.initListener();
        mBinding.btnTest.setOnClickListener(v -> ARouter.getInstance().build(PageRouter.TEST_GRID_VIEW).navigation());

        mBinding.btnProxy.setOnClickListener(v -> ToastUtil.INSTANCE.show(getContext(),"是否启用网络代理："+(isWifiProxy()?"yes":"false")+"  ; \n是否启用VPN : "+(isVpnUsed()?"yes":"false")));

    }

    private boolean isWifiProxy(){
        final boolean is_ics_or_later = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        String proxyAddress;
        int proxyPort;
        if (is_ics_or_later) {
            proxyAddress = System.getProperty("http.proxyHost");
            String portstr = System.getProperty("http.proxyPort");
            proxyPort = Integer.parseInt((portstr != null ? portstr : "-1"));
            System.out.println(proxyAddress + "~");
            System.out.println("port = " + proxyPort);
        }else {
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
            if(niList != null) {
                for (NetworkInterface intf : Collections.list(niList)) {
                    if(!intf.isUp() || intf.getInterfaceAddresses().size() == 0) {
                        continue;
                    }
                    Log.d(TAG, "isVpnUsed() NetworkInterface Name: " + intf.getName());
                    if ("tun0".equals(intf.getName()) || "ppp0".equals(intf.getName())){
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
