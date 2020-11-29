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

package com.wonium.cicada.ui.activity;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.cicada.R;
import com.wonium.cicada.databinding.ActivityMainBinding;
import com.wonium.cicada.router.PageRouter;
import com.wonium.cicada.ui.fragment.AccountFragment;
import com.wonium.cicada.ui.fragment.FindFragment;
import com.wonium.cicada.ui.fragment.FriendFragment;
import com.wonium.cicada.ui.fragment.MyFragment;
import com.wonium.cicada.ui.fragment.VideoFragment;
import com.wonium.cicada.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * @ClassName: MainActivity
 * @Description: 添加描述
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/11/28 14:14
 * @UpdateUser: 添加更新者
 * @UpdateDate: 2018/11/28 14:14
 * @UpdateDescription: 更新描述
 * @Version:
 */
@Route(path = PageRouter.ACTIVITY_MAIN)
public class MainActivity extends BaseActivity {
    private ActivityMainBinding mBinding;
    private List<Fragment> fragments;

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
     * @return
     */
    @Override
    protected int getStatusColor () {
        return getResources().getColor(R.color.black);
    }

    @Override
    public void initListener() {
        mBinding.includeMainContent.bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            int itemId;
            switch (menuItem.getItemId()) {
                case R.id.nav_find:
                    itemId =0;
                    break;
                case R.id.nav_video:
                    itemId=1;
                    break;
                case R.id.nav_my:
                    itemId =2;
                    break;
                case R.id.nav_friend:
                    itemId=3;
                    break;
                case R.id.nav_account:
                    itemId=4;
                    break;
                default:
                    itemId=0;
                    break;
            }
            replaceFragment(lastShowFragment,itemId);
            return true;
        });
    }
    private int lastShowFragment=0;

    private void initFragment() {

        fragments = new ArrayList<>();
        fragments.add(FindFragment.newInstance("FindFragment", "0"));
        fragments.add(VideoFragment.newInstance("VideoFragment", "1"));
        fragments.add(MyFragment.newInstance("MyFragment","2"));
        fragments.add(FriendFragment.newInstance("FriendFragment","3"));
        fragments.add(AccountFragment.newInstance("AccountFragment","4"));
        getSupportFragmentManager()
                .beginTransaction()
                .add(mBinding.includeMainContent.container.getId(),fragments.get(0))
                .show(fragments.get(0))
                .commitAllowingStateLoss();
    }

    private  void replaceFragment(int lastShowFragment,int index){
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments.get(lastShowFragment));
        this.lastShowFragment =index;
        if (!fragments.get(index).isAdded()){
            transaction.add(mBinding.includeMainContent.container.getId(),fragments.get(index));
        }
        transaction.show(fragments.get(index)).commitAllowingStateLoss();
    }



}
