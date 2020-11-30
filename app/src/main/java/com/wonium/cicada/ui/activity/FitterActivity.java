package com.wonium.cicada.ui.activity;

import androidx.appcompat.widget.LinearLayoutCompat;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.cicada.R;
import com.wonium.cicada.databinding.ActivityFitterBinding;
import com.wonium.cicada.router.PageRouter;
import com.wonium.cicada.base.BaseActivity;
import com.wonium.hydrogen.utils.DensityUtil;

@Route(path = PageRouter.ACTIVITY_FITTER)
public class FitterActivity extends BaseActivity {
    private ActivityFitterBinding mBinding;

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_fitter;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = ActivityFitterBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    public void initView() {
        LinearLayoutCompat.LayoutParams compat = (LinearLayoutCompat.LayoutParams) mBinding.layout.getLayoutParams();
        StringBuffer buffer = new StringBuffer();
        buffer.append("width->").append(compat.width);
        buffer.append("\n");
        buffer.append("height->").append(compat.height);
        buffer.append("scaleDensity-> ").append(DensityUtil.getInstance().getScaledDensity(getContext()));
        buffer.append("density-> ").append(DensityUtil.getInstance().getDensity(getContext()));
        buffer.append("densityDpi-> ").append(DensityUtil.getInstance().getDensityDpi(getContext()));
        mBinding.tv.setText(buffer);

    }

    @Override
    public void initListener() {

    }


    @Override
    protected int getStatusColor() {
        return R.color.black;
    }
}
