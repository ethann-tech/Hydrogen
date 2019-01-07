package com.wonium.aclj.ui.ui.frament;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.wonium.aclj.BR;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.FragmentAccountBinding;
import com.wonium.cicada.android.ui.fragment.BaseFragment;

/**
 * @ClassName: AccountFragment
 * @Description: 描述一下
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/12/2 16:56
 * @UpdateUser: update user
 * @UpdateDate: 2018/12/2 16:56
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
public class AccountFragment extends BaseFragment {
    private FragmentAccountBinding mBinding;
    private String argument1;
    private String argument2;
    public static AccountFragment newInstance(String argument1, String argument2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString("argument1", argument1);
        args.putString("argument2", argument2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void lazyload() {

    }

    @Override
    public void initArguments(String argument1, String argument2) {
        super.initArguments(argument1, argument2);
        this.argument1 =argument1;
        this.argument2 =argument2;
    }

    @Override
    public int getLayoutRedId() {
        return R.layout.fragment_account;
    }

    @Override
    public void bindLayout(View view) {
        mBinding =DataBindingUtil.bind(view);
    }

    @Override
    public void initView(View view) {
        mBinding.setVariable(BR.btnAccount,argument1);
    }

    @Override
    public void initListener() {

    }
}
