package com.wonium.aclj.ui.ui.activity;


import android.databinding.DataBindingUtil;

import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivityLoginBinding;
import com.wonium.cicada.android.BaseActivity;
import com.wonium.cicada.android.utils.IntentUtil;

/**
 * @ClassName: LoginActivity
 * @Description: 描述一下
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/11/29 23:46
 * @UpdateUser: update user
 * @UpdateDate: 2018/11/29 23:46
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
public class LoginActivity extends BaseActivity {
    private ActivityLoginBinding mBinding;
    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(true);
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
        mBinding.tvRegisterAccount.setOnClickListener(v -> IntentUtil.INSTANCE.toActivity(this,RegisterActivity.class));
    }



    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }


}
