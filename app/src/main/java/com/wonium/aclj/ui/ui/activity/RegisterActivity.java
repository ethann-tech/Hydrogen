package com.wonium.aclj.ui.ui.activity;

import android.databinding.DataBindingUtil;

import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivityRegisterBinding;
import com.wonium.cicada.android.BaseActivity;
import com.wonium.cicada.android.utils.StatusBarUtil;

/**
 * @ClassName: RegisterActivity
 * @Description: 描述一下
 * @Author: Ethan
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/12/12 22:17
 * @UpdateUser: update user
 * @UpdateDate: 2018/12/12 22:17
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
public class RegisterActivity extends BaseActivity {
    private ActivityRegisterBinding mBinding;
    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding =DataBindingUtil.setContentView(this,layoutResId);
    }

    @Override
    public void initView() {
        StatusBarUtil.INSTANCE.setColor(this,getResources().getColor(R.color.whiteSmoke));
    }

    @Override
    public void initListener() {

    }
}
