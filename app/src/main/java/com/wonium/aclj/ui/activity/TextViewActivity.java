package com.wonium.aclj.ui.activity;


import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivityTextViewBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.cicada.ui.BaseActivity;
@Route(path = PageRouter.ACTIVITY_TEXT_VIEW)

public class TextViewActivity extends BaseActivity {
    private ActivityTextViewBinding mBinding;
    @Override
    protected int getStatusColor() {
        return getResources().getColor(R.color.black);
    }

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_text_view;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = DataBindingUtil.setContentView(this,layoutResId);
    }

    @Override
    public void initView() {
        setSupportActionBar(mBinding.includeTextViewToolbar.toolbar);
        mBinding.setTitle(getContext().getResources().getString(R.string.activity_text_view));
        mBinding.includeTextViewToolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mBinding.includeTextViewToolbar.toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public void initListener() {

    }
}
