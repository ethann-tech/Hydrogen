package com.ethan.hydrogen.demo.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.ethan.hydrogen.demo.databinding.ActivityCrossViewBinding;
import com.ethan.hydrogen.demo.router.PageRouter;
import com.ethan.hydrogen.demo.base.BaseActivity;


/**
 * @ClassName:
 * @Description:
 * @Author: Ethan
 * @E-mail: ethan.nj@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2020/11/29 23:07
 * @UpdateUser: Ethan
 * @UpdateDate: 2020/11/29 23:07
 * @UpdateDescription: 更新说明
 * @Version: 1.0.1
 */
@Route(path = PageRouter.ACTIVITY_CROSS_VIEW)
public class CrossViewActivity extends BaseActivity {
    private ActivityCrossViewBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding.sampleCrossView.setOnClickListener(v -> mBinding.sampleCrossView.toggle());
        mBinding.sampleCrossView.setColor(getResources().getColor(com.ethan.hydrogen.demo.R.color.cross_view_stroke_color));
    }

    @Override
    protected int getStatusColor() {
        return getResources().getColor(com.ethan.hydrogen.R.color.black);
    }

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
    }

    @Override
    public int getLayoutResId() {
        return com.ethan.hydrogen.demo.R.layout.activity_cross_view;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = ActivityCrossViewBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }
}
