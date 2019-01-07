package com.wonium.aclj.ui.ui.frament;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.wonium.aclj.BR;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.FragmentFindBinding;
import com.wonium.aclj.ui.ui.activity.H5Activity;
import com.wonium.extension.utils.StringUtil;
import com.wonium.cicada.android.ui.fragment.BaseFragment;


/**
 * @ClassName: FindFragment
 * @Description: 描述一下
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/12/2 15:11
 * @UpdateUser: update user
 * @UpdateDate: 2018/12/2 15:11
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
public class FindFragment extends BaseFragment {

    private FragmentFindBinding mBinding;
    private String argument1;

    public static Fragment newInstance(String argument1, String argument2) {
        FindFragment fragment = new FindFragment();
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
    public void initView(View view) {
        mBinding.setVariable(BR.btnFind,StringUtil.INSTANCE.isEmpty(argument1));
    }

    @Override
    public void initArguments(String argument1, String argument2) {
        this.argument1=argument1;
        String argument21 = argument2;
    }

    @Override
    public int getLayoutRedId() {
        return R.layout.fragment_find;
    }

    @Override
    public void bindLayout(View view) {
        mBinding = DataBindingUtil.bind(view);
    }


    @Override
    public void initListener() {
        mBinding.btnH5.setOnClickListener(v -> startActivity(new Intent(getContext(),H5Activity.class)));
    }
}
