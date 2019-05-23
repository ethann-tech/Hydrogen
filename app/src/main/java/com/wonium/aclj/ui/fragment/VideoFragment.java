package com.wonium.aclj.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wonium.aclj.R;
import com.wonium.aclj.databinding.FragmentVideoBinding;
import com.wonium.cicada.ui.BaseFragment;
import com.wonium.cicada.utils.ToastUtil;

import androidx.databinding.DataBindingUtil;

public class VideoFragment extends BaseFragment {
    private FragmentVideoBinding mBinding;
    private String args1;
    private String args2;
    public static VideoFragment newInstance(String args1,String args2) {
        Bundle args = new Bundle();
        VideoFragment fragment = new VideoFragment();
        fragment.args1 =args1;
        fragment.args1 =args2;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_video;
    }

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {
        mBinding = DataBindingUtil.inflate(inflater,getLayoutResId(),container,false);
        return mBinding.getRoot();
    }

    @Override
    protected void initView(View view) {
        mBinding.editFixedText.setFixedText("ZH-");
        mBinding.btnTest.setOnClickListener(v -> ToastUtil.getInstance().show(getContext(),mBinding.editFixedText.getText().toString().trim()));
    }

    @Override
    protected void initListener() {
        super.initListener();
    }
}
