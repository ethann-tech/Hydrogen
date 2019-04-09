package com.wonium.aclj.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wonium.aclj.R;
import com.wonium.aclj.databinding.FragmentAccountBinding;
import com.wonium.aclj.databinding.FragmentFindBinding;
import com.wonium.cicada.ui.BaseFragment;

import androidx.databinding.DataBindingUtil;

public class AccountFragment extends BaseFragment {
    private FragmentAccountBinding mBinding;
    private String args1;
    private String args2;


    public static AccountFragment newInstance(String args1, String args2) {
        Bundle args = new Bundle();
        AccountFragment fragment = new AccountFragment();
        fragment.args1 =args1;
        fragment.args2 =args2;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_account;
    }

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {
        mBinding = DataBindingUtil.inflate(inflater,getLayoutResId(),container,false);
        return mBinding.getRoot();
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initListener() {
        super.initListener();
    }
}
