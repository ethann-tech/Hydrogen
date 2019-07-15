package com.wonium.aclj.ui.activity;

import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivityRippleLayoutBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.cicada.ui.BaseActivity;

@Route(path = PageRouter.ACTIVITY_RIPPLE_LAYOUT)
public class RippleLayoutActivity extends BaseActivity {
    private ActivityRippleLayoutBinding mBinding;
    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_ripple_layout;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding= DataBindingUtil.setContentView(this,layoutResId);
    }

    @Override
    public void initView() {
        mBinding.setTitle(getResources().getString(R.string.activity_ripple_layout));
        setSupportActionBar(mBinding.includeRippleToolbar.toolbar);
        mBinding.includeRippleToolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mBinding.includeRippleToolbar.toolbar.setNavigationOnClickListener(v -> finish());

    }

    @Override
    public void initListener() {

    }
}
