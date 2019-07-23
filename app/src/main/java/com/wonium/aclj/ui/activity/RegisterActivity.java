package com.wonium.aclj.ui.activity;

import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivityRegisterBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.cicada.ui.BaseActivity;
@Route(path = PageRouter.ACTIVITY_REGISTER)
public class RegisterActivity extends BaseActivity {
    private ActivityRegisterBinding mBinding;
    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(true);
        setScreenRotate(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_register;
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
        mBinding.tvRegisterBackLogin.setOnClickListener(v -> finish());
    }
}
