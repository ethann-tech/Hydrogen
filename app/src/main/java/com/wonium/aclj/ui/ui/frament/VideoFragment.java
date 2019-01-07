package com.wonium.aclj.ui.ui.frament;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.wonium.aclj.R;
import com.wonium.aclj.databinding.FragmentVideoBinding;
import com.wonium.cicada.android.ui.fragment.BaseFragment;

/**
 * @ClassName: VideoFragment
 * @Description: 描述一下
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/12/2 16:46
 * @UpdateUser: update user
 * @UpdateDate: 2018/12/2 16:46
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
public class VideoFragment extends BaseFragment {

    private FragmentVideoBinding mBinding;
    public static VideoFragment newInstance(String argument1, String argument2) {
        Bundle args = new Bundle();
        VideoFragment fragment = new VideoFragment();
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
    }

    @Override
    public int getLayoutRedId() {
        return R.layout.fragment_video;
    }

    @Override
    public void bindLayout(View view) {
        mBinding = DataBindingUtil.bind(view);
    }

    @Override
    public void initListener() {
    }
}
