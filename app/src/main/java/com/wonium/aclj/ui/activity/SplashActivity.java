package com.wonium.aclj.ui.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivitySplashBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.cicada.ui.BaseActivity;
import com.wonium.cicada.utils.DensityUtil;
import com.wonium.cicada.utils.SharedPreferencesUtil;
import com.wonium.hydrogen.adapter.GuidePagerAdapter;
import com.wonium.hydrogen.config.AppConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Route(path = PageRouter.ACTIVITY_SPLASH)
public class SplashActivity extends BaseActivity {
    private int[] images = new int[]{R.drawable.indicator_select, R.drawable.indicator_unselect};
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
        mBinding = DataBindingUtil.setContentView(this, layoutResId);
    }

    @Override
    public void initView() {
        guideList = getGuideList();
        isFirstLauncher();

    }

    private void isFirstLauncher() {
        boolean isFirst = SharedPreferencesUtil.getInstance().getBoolean(this, AppConfig.IS_FIRST_LAUNCHER, true);
        if (isFirst) {
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
                if (position == guideList.size() - 1) {
                    mBinding.btnInto.setVisibility(View.VISIBLE);
                } else {
                    mBinding.btnInto.setVisibility(View.GONE);
                }
                setCurrentDot(position);
            }
        });
    }


    public void setCurrentDot(int position) {
        for (int i = 0; i < mBinding.splashViewGroup.getChildCount(); i++) {
            ((AppCompatImageView) mBinding.splashViewGroup.getChildAt(i)).setImageResource(i == position ? images[0] : images[1]);
        }
    }


    /**
     * 初始化指示器
     */
    private void initIndicator() {
        for (int i = 0; i < guideList.size(); i++) {
            AppCompatImageView img = new AppCompatImageView(SplashActivity.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.getInstance().dip2px(getContext(), 10), DensityUtil.getInstance().dip2px(getContext(), 10));
            params.setMargins(10, 0, 10, 0);
            img.setLayoutParams(params);
            if (i == 0) {
                img.setImageDrawable(getResources().getDrawable(images[0]));
            } else {
                img.setImageDrawable(getResources().getDrawable(images[1]));
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
                ARouter.getInstance().build(PageRouter.ACTIVITY_MAIN).navigation(getContext());
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
        for (int i = 0; i < len; i++) {
            guideList.add(array.getResourceId(i, 0));
        }
        return guideList;
    }

}
