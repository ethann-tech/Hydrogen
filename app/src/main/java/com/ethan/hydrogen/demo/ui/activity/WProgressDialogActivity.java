/*
 * Copyright (C) 2019 Ethan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ethan.hydrogen.demo.ui.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;



import com.ethan.hydrogen.demo.databinding.ActivityWProgressViewBinding;
import com.ethan.hydrogen.demo.router.PageRouter;
import com.ethan.hydrogen.demo.base.BaseActivity;
import com.ethan.hydrogen.ui.progress.WProgressDialog;
import com.ethan.hydrogen.demo.R;

import zlc.season.butterfly.annotation.Agile;

@Agile(scheme = PageRouter.ACTIVITY_W_PROGRESS_DIALOG)
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
        mBinding = ActivityWProgressViewBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    public void initView() {
        mBinding.includeWProgressToolbar.tvToolbarTitle.setText(getString(R.string.activity_w_progress_dialog));
        setContentView(mBinding.getRoot());
        setSupportActionBar(mBinding.includeWProgressToolbar.toolbar);
        mBinding.includeWProgressToolbar.toolbar.setNavigationIcon(com.ethan.hydrogen.R.drawable.ic_arrow_back_24dp);
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
        int id = v.getId();
        if (id == R.id.indeterminate) {
            dialog = WProgressDialog.create(this)
                    .setStyle(WProgressDialog.Style.SPIN_INDETERMINATE);
            scheduleDismiss();
        } else if (id == R.id.label_indeterminate) {
            dialog = WProgressDialog.create(this)
                    .setStyle(WProgressDialog.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait")
                    .setCancellable(dialogInterface -> Toast.makeText(WProgressDialogActivity.this, "You " +
                            "cancelled manually!", Toast
                            .LENGTH_SHORT).show());

            scheduleDismiss();
        } else if (id == R.id.detail_indeterminate) {
            dialog = WProgressDialog.create(this)
                    .setStyle(WProgressDialog.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait")
                    .setDetailsLabel("Downloading data");
            scheduleDismiss();
        } else if (id == R.id.grace_indeterminate) {
            dialog = WProgressDialog.create(this)
                    .setStyle(WProgressDialog.Style.SPIN_INDETERMINATE)
                    .setGraceTime(1000);
            scheduleDismiss();
        } else if (id == R.id.determinate) {
            dialog = WProgressDialog.create(WProgressDialogActivity.this)
                    .setStyle(WProgressDialog.Style.PIE_DETERMINATE)
                    .setLabel("Please wait");
            simulateProgressUpdate();
        } else if (id == R.id.annular_determinate) {
            dialog = WProgressDialog.create(WProgressDialogActivity.this)
                    .setStyle(WProgressDialog.Style.ANNULAR_DETERMINATE)
                    .setLabel("Please wait")
                    .setDetailsLabel("Downloading data");
            simulateProgressUpdate();
        } else if (id == R.id.bar_determinate) {
            dialog = WProgressDialog.create(WProgressDialogActivity.this)
                    .setStyle(WProgressDialog.Style.BAR_DETERMINATE)
                    .setLabel("Please wait");
            simulateProgressUpdate();
        } else if (id == R.id.custom_view) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(com.ethan.hydrogen.R.drawable.loading_animation);
            AnimationDrawable drawable = (AnimationDrawable) imageView.getBackground();
            drawable.start();
            dialog = WProgressDialog.create(this)
                    .setCustomView(imageView)
                    .setLabel("This is a custom view");
            scheduleDismiss();
        } else if (id == R.id.dim_background) {
            dialog = WProgressDialog.create(this)
                    .setStyle(WProgressDialog.Style.SPIN_INDETERMINATE)
                    .setDimAmount(0.5f);
            scheduleDismiss();
        } else if (id == R.id.custom_color_animate) {//noinspection deprecation
            dialog = WProgressDialog.create(this)
                    .setStyle(WProgressDialog.Style.SPIN_INDETERMINATE)
                    .setWindowColor(getResources().getColor(R.color.colorPrimary))
                    .setAnimationSpeed(2);
            scheduleDismiss();
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
