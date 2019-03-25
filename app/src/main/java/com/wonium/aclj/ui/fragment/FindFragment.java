package com.wonium.aclj.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.FragmentFindBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.cicada.ui.BaseFragment;

import androidx.databinding.DataBindingUtil;

public class FindFragment extends BaseFragment {
    private FragmentFindBinding mBinding;
    private String args1;
    private String args2;


    public static FindFragment newInstance(String args1,String args2) {
        Bundle args = new Bundle();
        FindFragment fragment = new FindFragment();
        fragment.args1 =args1;
        fragment.args2 =args2;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_find;
    }

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {
        mBinding = DataBindingUtil.inflate(inflater,getLayoutResId(),container,false);
        return mBinding.getRoot();
    }

    @Override
    protected void initView(View view) {
        mBinding.setBtnFind("TestGridView");
    }

    @Override
    protected void initListener() {
        super.initListener();
        mBinding.btnTest.setOnClickListener(v -> ARouter.getInstance().build(PageRouter.TEST_GRID_VIEW).navigation());
    }
}
