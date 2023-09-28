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

import android.content.res.TypedArray;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatImageView;

import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ethan.hydrogen.demo.databinding.ActivitySplashBinding;
import com.ethan.hydrogen.demo.router.PageRouter;
import com.ethan.hydrogen.demo.base.BaseActivity;
import com.ethan.hydrogen.utils.DensityUtil;
import com.ethan.hydrogen.utils.SharedPreferencesUtil;
import com.ethan.hydrogen.demo.adapter.GuidePagerAdapter;
import com.ethan.hydrogen.demo.config.AppConfig;
import com.ethan.hydrogen.demo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends BaseActivity {
    private final int[] images = new int[]{R.drawable.indicator_select, R.drawable.indicator_unselect};
    private List<Integer> guideList = new ArrayList<>();
    private ActivitySplashBinding mBinding;

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(true);
        setScreenRotate(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    public void initView() {
        guideList = getGuideList();
        isFirstLauncher();

    }

    private void isFirstLauncher() {
        boolean isFirst = SharedPreferencesUtil.getInstance().getBoolean(this, AppConfig.IS_FIRST_LAUNCHER, true);
        if(isFirst) {
            launcherGuide();
        } else {
            launcherSplash();
        }
    }

    /**
     * 引导页
     */
    private void launcherGuide() {
        mBinding.imgLauncher.setVisibility(View.GONE);
        mBinding.splashLayoutGuide.setVisibility(View.VISIBLE);
        initIndicator();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        mBinding.btnInto.setAnimation(animation);
        mBinding.splashViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        GuidePagerAdapter adapter = new GuidePagerAdapter(getContext());
        adapter.setDatas(guideList);
        mBinding.splashViewPager.setAdapter(adapter);
        mBinding.splashViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(position == guideList.size() - 1) {
                    mBinding.btnInto.setVisibility(View.VISIBLE);
                } else {
                    mBinding.btnInto.setVisibility(View.GONE);
                }
                setCurrentDot(position);
            }
        });
    }


    public void setCurrentDot(int position) {
        for(int i = 0; i < mBinding.splashViewGroup.getChildCount(); i++) {
            ((AppCompatImageView) mBinding.splashViewGroup.getChildAt(i)).setImageResource(i == position ? images[0] : images[1]);
        }
    }


    /**
     * 初始化指示器
     */
    private void initIndicator() {
        for(int i = 0; i < guideList.size(); i++) {
            AppCompatImageView img = new AppCompatImageView(SplashActivity.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.getInstance().dip2px(getContext(), 10),
                    DensityUtil.getInstance().dip2px(getContext(), 10));
            params.setMargins(10, 0, 10, 0);
            img.setLayoutParams(params);
            if(i == 0) {
                img.setImageDrawable(ResourcesCompat.getDrawable(getResources(), images[0], null));
            } else {
                img.setImageDrawable(ResourcesCompat.getDrawable(getResources(), images[1], null));
            }
            mBinding.splashViewGroup.addView(img);
        }
    }

    /**
     * 启动页面
     */
    private void launcherSplash() {
        mBinding.splashLayoutGuide.setVisibility(View.GONE);
        mBinding.imgLauncher.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        mBinding.imgLauncher.startAnimation(animation);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                MainActivity.start(getContext());
                finish();
            }
        }, 3000);
    }

    @Override
    public void initListener() {
        mBinding.btnInto.setOnClickListener(v -> {
            SharedPreferencesUtil.getInstance().setBoolean(getContext(), AppConfig.IS_FIRST_LAUNCHER, false);
            ARouter.getInstance().build(PageRouter.ACTIVITY_MAIN).navigation(getContext());
            finish();
        });
    }

    public List<Integer> getGuideList() {
        TypedArray array = getResources().obtainTypedArray(R.array.item_splash);
        int len = array.length();
        List<Integer> guideList = new ArrayList<>(len);
        for(int i = 0; i < len; i++) {
            guideList.add(array.getResourceId(i, 0));
        }
        array.recycle();
        return guideList;
    }

}
