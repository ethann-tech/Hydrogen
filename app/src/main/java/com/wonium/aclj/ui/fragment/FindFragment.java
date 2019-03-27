package com.wonium.aclj.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.FragmentFindBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.cicada.ui.BaseFragment;
import com.wonium.cicada.utils.ToastUtil;

import androidx.databinding.DataBindingUtil;


/**
 * @ClassName: FindFragment
 * @Description: 描述一下
 * @Author: Ethan
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2019/3/27 21:21
 * @UpdateUser: update user
 * @UpdateDate: 2019/3/27 21:21
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
public class FindFragment extends BaseFragment {
    private final String TAG =FindFragment.this.getClass().getSimpleName();
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
        mBinding.btnTest.setOnClickListener(v -> {
             ARouter.getInstance().build(PageRouter.TEST_GRID_VIEW).navigation();
//            Log.d("test", activityTest.toString());
        });
    }
}
