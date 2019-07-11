package com.wonium.aclj.ui.activity;

import android.Manifest;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivityIntentUtilBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.cicada.ui.BaseActivity;
import com.wonium.cicada.utils.IntentUtil;
import com.wonium.cicada.utils.ToastUtil;

import java.security.Permission;

import ru.alexbykov.nopermission.PermissionHelper;


@Route(path = PageRouter.ACTIVITY_INTENT_UTIL)
public class IntentUtilActivity extends BaseActivity {
    private ActivityIntentUtilBinding mBinding;
    private PermissionHelper mPermissionHelper;

    @Override
    public void initWindowAttributes() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_intent_util;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = DataBindingUtil.setContentView(this, layoutResId);
    }

    @Override
    public void initView() {
        mBinding.setTitle(getResources().getString(R.string.tools_intent));
        setSupportActionBar(mBinding.includeIntentUtil.toolbar);
        mBinding.includeIntentUtil.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mPermissionHelper = new PermissionHelper(this);
    }

    @Override
    public void initListener() {
        mBinding.includeIntentUtil.toolbar.setNavigationOnClickListener(v -> finish());

        mBinding.btnIntentToCallPhone.setOnClickListener(v -> checkPermissionForCallPhone());
    }

    private void checkPermissionForCallPhone() {
        mPermissionHelper.check(Manifest.permission.CALL_PHONE).onDenied(() -> ToastUtil.getInstance().show(getContext(), "没有授权，无法打开拨号页面")).onSuccess(() -> IntentUtil.getInstance().callPhone(getContext(), "18751705121")).onNeverAskAgain(() -> ToastUtil.getInstance().show(getContext(), "不再询问")).run();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionHelper.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
