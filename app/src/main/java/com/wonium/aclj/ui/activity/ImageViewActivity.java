package com.wonium.aclj.ui.activity;

import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivityImageViewBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.cicada.ui.BaseActivity;

/**
 * @ClassName: ImageViewActivity
 * @Description:
 * @author: dahai
 * @E-mail:
 * @Blog:
 * @CreateDate: 2019/6/3 17:46
 * @UpdateUser: dahai
 * @UpdateDate: 2019/6/3 17:46
 * @UpdateDescription:
 * @Version:
 */
@Route(path = PageRouter.ACTIVITY_IMAGE_VIEW)
public class ImageViewActivity extends BaseActivity {
    private ActivityImageViewBinding mBinding;

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
        return R.layout.activity_image_view;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = DataBindingUtil.setContentView(this, layoutResId);
    }

    @Override
    public void initView() {
        setStatusBar(true);
        setSupportActionBar(mBinding.includeImageViewToolbar.toolbar);
        mBinding.setTitle(getResources().getString(R.string.activity_image_view));
        mBinding.includeImageViewToolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
    }

    @Override
    public void initListener() {
        mBinding.includeImageViewToolbar.toolbar.setNavigationOnClickListener(v -> finish());
    }
}
