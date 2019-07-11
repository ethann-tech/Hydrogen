package com.wonium.aclj.ui.activity;



import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivityInputMethodManagerUtilBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.cicada.ui.BaseActivity;
import com.wonium.cicada.utils.InputMethodManagerUtil;

@Route(path = PageRouter.ACTIVITY_INPUT_METHOD_MANAGER_UTIL)
public class InputMethodManagerUtilActivity extends BaseActivity {
    private ActivityInputMethodManagerUtilBinding mBinding;

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
        setScreenRotate(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_input_method_manager_util;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = DataBindingUtil.setContentView(this,layoutResId);
    }

    @Override
    public void initView() {
        mBinding.setTitle(getString(R.string.tools_input_method_manager));
        mBinding.includeImmuToolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);

    }

    @Override
    public void initListener() {
        mBinding.includeImmuToolbar.toolbar.setNavigationOnClickListener(v -> finish());
    }

    public void onOpenSoftKeyBoard(View view) {
        InputMethodManagerUtil.getInstance(getContext()).toggleSoftInput();
    }

    public void onCloseSoftKeyBoard(View view) {
        InputMethodManagerUtil.getInstance(getContext()).hideSoftInput(this);
    }
}
