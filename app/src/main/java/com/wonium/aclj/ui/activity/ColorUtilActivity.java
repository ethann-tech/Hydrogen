package com.wonium.aclj.ui.activity;

import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivityColorUtilBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.cicada.ui.BaseActivity;
@Route(path = PageRouter.ACTIVITY_COLOR_UTIL)
public class ColorUtilActivity extends BaseActivity {
    private ActivityColorUtilBinding mBinding;
    @Override
    protected int getStatusColor() {
        return getResources().getColor(R.color.black);
    }

    @Override
    public void initWindowAttributes() {
            setAllowFullScreen(false);
            setScreenRoate(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_color_util;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = DataBindingUtil.setContentView(this,layoutResId);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }
}
