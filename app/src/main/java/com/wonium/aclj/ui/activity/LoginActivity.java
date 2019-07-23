package com.wonium.aclj.ui.activity;

import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivityLoginBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.cicada.ui.BaseActivity;

@Route(path = PageRouter.ACTIVITY_LOGIN)
public class LoginActivity extends BaseActivity {
    private ActivityLoginBinding mBinding;

    @Override
    protected int getStatusColor() {
        return getResources().getColor(R.color.black);
    }

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(true);
        setScreenRotate(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = DataBindingUtil.setContentView(this, layoutResId);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        mBinding.btnLogin.setOnClickListener(v -> ARouter.getInstance().build(PageRouter.ACTIVITY_MAIN).navigation(getContext()));
        mBinding.tvRegisterAccount.setOnClickListener(v -> ARouter.getInstance().build(PageRouter.ACTIVITY_REGISTER).navigation(getContext()));
    }
}
