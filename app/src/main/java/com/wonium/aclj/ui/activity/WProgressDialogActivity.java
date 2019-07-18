package com.wonium.aclj.ui.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivityWProgressViewBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.cicada.ui.BaseActivity;
import com.wonium.cicada.ui.progress.WProgressDialog;


@Route(path = PageRouter.ACTIVITY_W_PROGRESS_DIALOG)
public class WProgressDialogActivity extends BaseActivity implements View.OnClickListener {
    private ActivityWProgressViewBinding mBinding;
    private WProgressDialog dialog;
    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_w_progress_view;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = DataBindingUtil.setContentView(this,layoutResId);
    }

    @Override
    public void initView() {
        mBinding.setTitle(getString(R.string.activity_w_progress_dialog));
        setSupportActionBar(mBinding.includeWProgressToolbar.toolbar);
        mBinding.includeWProgressToolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
    }

    @Override
    public void initListener() {
        mBinding.includeWProgressToolbar.toolbar.setNavigationOnClickListener(v -> finish());
        mBinding.indeterminate.setOnClickListener(this);
        mBinding.labelIndeterminate.setOnClickListener(this);
        mBinding.detailIndeterminate.setOnClickListener(this);
        mBinding.graceIndeterminate.setOnClickListener(this);
        mBinding.determinate.setOnClickListener(this);
        mBinding.annularDeterminate.setOnClickListener(this);
        mBinding. barDeterminate.setOnClickListener(this);
        mBinding.customView.setOnClickListener(this);
        mBinding.dimBackground.setOnClickListener(this);
        mBinding.customColorAnimate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.indeterminate:
                dialog = WProgressDialog.create(this)
                        .setStyle(WProgressDialog.Style.SPIN_INDETERMINATE);
                scheduleDismiss();
                break;
            case R.id.label_indeterminate:
                dialog = WProgressDialog.create(this)
                        .setStyle(WProgressDialog.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setCancellable(dialogInterface -> Toast.makeText(WProgressDialogActivity.this, "You " +
                                "cancelled manually!", Toast
                                .LENGTH_SHORT).show());

                scheduleDismiss();
                break;
            case R.id.detail_indeterminate:
                dialog = WProgressDialog.create(this)
                        .setStyle(WProgressDialog.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setDetailsLabel("Downloading data");
                scheduleDismiss();
                break;
            case R.id.grace_indeterminate:
                dialog = WProgressDialog.create(this)
                        .setStyle(WProgressDialog.Style.SPIN_INDETERMINATE)
                        .setGraceTime(1000);
                scheduleDismiss();
                break;
            case R.id.determinate:
                dialog = WProgressDialog.create(WProgressDialogActivity.this)
                        .setStyle(WProgressDialog.Style.PIE_DETERMINATE)
                        .setLabel("Please wait");
                simulateProgressUpdate();
                break;
            case R.id.annular_determinate:
                dialog = WProgressDialog.create(WProgressDialogActivity.this)
                        .setStyle(WProgressDialog.Style.ANNULAR_DETERMINATE)
                        .setLabel("Please wait")
                        .setDetailsLabel("Downloading data");
                simulateProgressUpdate();
                break;
            case R.id.bar_determinate:
                dialog = WProgressDialog.create(WProgressDialogActivity.this)
                        .setStyle(WProgressDialog.Style.BAR_DETERMINATE)
                        .setLabel("Please wait");
                simulateProgressUpdate();
                break;
            case R.id.custom_view:
                ImageView imageView = new ImageView(this);
                imageView.setBackgroundResource(R.drawable.loading_animation);
                AnimationDrawable drawable = (AnimationDrawable) imageView.getBackground();
                drawable.start();
                dialog = WProgressDialog.create(this)
                        .setCustomView(imageView)
                        .setLabel("This is a custom view");
                scheduleDismiss();
                break;
            case R.id.dim_background:
                dialog = WProgressDialog.create(this)
                        .setStyle(WProgressDialog.Style.SPIN_INDETERMINATE)
                        .setDimAmount(0.5f);
                scheduleDismiss();
                break;
            case R.id.custom_color_animate:
                //noinspection deprecation
                dialog = WProgressDialog.create(this)
                        .setStyle(WProgressDialog.Style.SPIN_INDETERMINATE)
                        .setWindowColor(getResources().getColor(R.color.colorPrimary))
                        .setAnimationSpeed(2);
                scheduleDismiss();
                break;
        }

        dialog.show();
    }


    private void simulateProgressUpdate() {
        dialog.setMaxProgress(100);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            int currentProgress;
            @Override
            public void run() {
                currentProgress += 1;
                dialog.setProgress(currentProgress);
                if (currentProgress == 80) {
                    dialog.setLabel("Almost finish...");
                }
                if (currentProgress < 100) {
                    handler.postDelayed(this, 50);
                }
            }
        }, 100);
    }

    private void scheduleDismiss() {
        Handler handler = new Handler();
        handler.postDelayed(() -> dialog.dismiss(), 2000);
    }

}
