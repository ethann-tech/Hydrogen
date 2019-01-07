package com.wonium.aclj.ui.ui.frament;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.wonium.aclj.BR;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.FragmentMyBinding;
import com.wonium.aclj.ui.ui.activity.LoginActivity;
import com.wonium.extension.utils.IntentUtil;
import com.wonium.cicada.android.ui.fragment.BaseFragment;

import java.util.Objects;

public class MyFragment extends BaseFragment {
    private FragmentMyBinding mBinding;
    private String argument1;
    private String argument2;
    public static MyFragment newInstance(String argument1, String argument2) {
        MyFragment fragment = new MyFragment();
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
    public void initView(View view) {
    mBinding.setVariable(BR.btnMy,argument1);
    mBinding.btnMy.setOnClickListener(v -> IntentUtil.INSTANCE.toActivity(Objects.requireNonNull(getContext()),LoginActivity.class));
    }


    @Override
    public int getLayoutRedId() {
        return R.layout.fragment_my;
    }

    @Override
    public void bindLayout(View view) {
        mBinding =DataBindingUtil.bind(view);

    }

    @Override
    public void initListener() {

    }
    public void onClick(View view){

    }


}
