package com.wonium.aclj.ui.activity;

import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivityDensityUtilBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.cicada.ui.BaseActivity;
import com.wonium.cicada.utils.DensityUtil;
@Route(path = PageRouter.ACTIVITY_DENSITY_UTIL)
public class DensityUtilActivity extends BaseActivity {
    private ActivityDensityUtilBinding mBinding;
    @Override
    protected int getStatusColor() {
        return getResources().getColor(R.color.black);
    }

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
        setScreenRotate(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_density_util;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = DataBindingUtil.setContentView(this,layoutResId);
    }

    @Override
    public void initView() {
        mBinding.setTitle(getResources().getString(R.string.tools_density));
        setSupportActionBar(mBinding.includeDensityUtilToolbar.toolbar);
        mBinding.includeDensityUtilToolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        StringBuilder builder =new StringBuilder();
        builder.append("DensityUtil是一个屏幕信息获取数值的转换类\n")
                .append("使用示例：\n")
        .append(" DensityUtil.getInstance().dip2px(getContext(),10)-->")
        .append( DensityUtil.getInstance().dip2px(getContext(),10));
        mBinding.setTestCase(builder.toString());
    }

    @Override
    public void initListener() {
        mBinding.includeDensityUtilToolbar.toolbar.setNavigationOnClickListener(v -> finish());
    }
}
