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


import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ethan.hydrogen.demo.R;
import com.ethan.hydrogen.demo.databinding.ActivityMainBinding;
import com.ethan.hydrogen.demo.router.PageRouter;
import com.ethan.hydrogen.demo.ui.fragment.AccountFragment;
import com.ethan.hydrogen.demo.ui.fragment.FindFragment;
import com.ethan.hydrogen.demo.ui.fragment.FriendFragment;
import com.ethan.hydrogen.demo.ui.fragment.MyFragment;
import com.ethan.hydrogen.demo.ui.fragment.VideoFragment;
import com.ethan.hydrogen.demo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * @author ethan
 */
public class MainActivity extends BaseActivity {
    private ActivityMainBinding mBinding;
    private List<Fragment> fragments;

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }


    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    public void initView() {
        // 开启状态栏沉浸
        setStatusBar(true);
        mBinding.includeToolbar.tvToolbarTitle.setText(getString(R.string.app_name));
        initFragment();
    }

    /**
     * 设置状态栏颜色
     *
     * @return
     */
    @Override
    protected int getStatusColor() {
        return getResources().getColor(com.ethan.hydrogen.R.color.black);
    }

    @Override
    public void initListener() {
        mBinding.includeMainContent.bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            int itemId;
            int id = menuItem.getItemId();
            if(id == R.id.nav_video) {
                itemId = 1;
            } else if(id == R.id.nav_my) {
                itemId = 2;
            } else if(id == R.id.nav_friend) {
                itemId = 3;
            } else if(id == R.id.nav_account) {
                itemId = 4;
            } else {
                itemId = 0;
            }
            replaceFragment(lastShowFragment, itemId);
            return true;
        });
    }

    private int lastShowFragment = 0;

    private void initFragment() {

        fragments = new ArrayList<>();
        fragments.add(FindFragment.newInstance("FindFragment", "0"));
        fragments.add(VideoFragment.newInstance("VideoFragment", "1"));
        fragments.add(MyFragment.newInstance("MyFragment", "2"));
        fragments.add(FriendFragment.newInstance("FriendFragment", "3"));
        fragments.add(AccountFragment.newInstance("AccountFragment", "4"));
        getSupportFragmentManager().beginTransaction().add(mBinding.includeMainContent.container.getId(), fragments.get(0)).show(fragments.get(0)).commitAllowingStateLoss();
    }

    private void replaceFragment(int lastShowFragment, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments.get(lastShowFragment));
        this.lastShowFragment = index;
        if(!fragments.get(index).isAdded()) {
            transaction.add(mBinding.includeMainContent.container.getId(), fragments.get(index));
        }
        transaction.show(fragments.get(index)).commitAllowingStateLoss();
    }


}
