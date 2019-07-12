package com.wonium.aclj.ui.activity;

import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivityMd5UtilBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.cicada.ui.BaseActivity;
import com.wonium.cicada.utils.Md5Util;

@Route(path = PageRouter.ACTIVITY_MD5_UTIL)
public class MD5UtilActivity extends BaseActivity {
    private ActivityMd5UtilBinding mBinding;
    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
        setScreenRotate(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_md5_util;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = DataBindingUtil.setContentView(this,layoutResId);
    }

    @Override
    public void initView() {
        setSupportActionBar(mBinding.includeMd5Toolbar.toolbar);
        mBinding.setTitle(getString(R.string.tools_md5));
        mBinding.includeMd5Toolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mBinding.includeMd5Toolbar.toolbar.setNavigationOnClickListener(v -> finish());
        StringBuilder builder =new StringBuilder();
        builder.append("原字符串 ： wonium\n")
        .append("加密后字符串： ").append(Md5Util.getInstance().getMD5String("wonium"));
        mBinding.setTestCase(builder.toString());
    }

    @Override
    public void initListener() {

    }
}
