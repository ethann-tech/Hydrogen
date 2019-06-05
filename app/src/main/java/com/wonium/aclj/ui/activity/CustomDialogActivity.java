package com.wonium.aclj.ui.activity;

import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivityCustomDialogBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.cicada.ui.BaseActivity;
import com.wonium.cicada.ui.widget.LoadingDialog;


@Route(path = PageRouter.ACTIVITY_CUSTOM_DIALOG)
public class CustomDialogActivity extends BaseActivity {
    private ActivityCustomDialogBinding mBinding;

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
        return R.layout.activity_custom_dialog;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = DataBindingUtil.setContentView(this, layoutResId);
    }

    @Override
    public void initView() {
        setStatusBar(true);
        setSupportActionBar(mBinding.includeCustomDialogToolbar.toolbar);
        mBinding.setTitle(getResources().getString(R.string.activity_custom_dialog));
        mBinding.includeCustomDialogToolbar.toolbar.setNavigationOnClickListener(v -> finish());
        mBinding.includeCustomDialogToolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
    }

    @Override
    public void initListener() {
        mBinding.btnLoadingDialog.setOnClickListener(v -> {
            LoadingDialog dialog = new LoadingDialog(CustomDialogActivity.this);
            dialog.show();
        });
    }
}
