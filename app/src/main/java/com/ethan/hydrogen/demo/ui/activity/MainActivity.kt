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
package com.ethan.hydrogen.demo.ui.activity

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.ethan.hydrogen.demo.R
import com.ethan.hydrogen.demo.base.BaseActivity
import com.ethan.hydrogen.demo.databinding.ActivityMainBinding
import com.ethan.hydrogen.demo.ui.fragment.AccountFragment
import com.ethan.hydrogen.demo.ui.fragment.FragmentTools
import com.ethan.hydrogen.demo.ui.fragment.FriendFragment
import com.ethan.hydrogen.demo.ui.fragment.MyFragment
import com.ethan.hydrogen.demo.ui.fragment.VideoFragment
import com.ethan.hydrogen.ui.BaseFragment
import com.ethan.hydrogen.utils.ResourceUtil

/**
 * @author ethan
 */
class MainActivity : BaseActivity() {

    private val mBinding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val fragments: MutableList<BaseFragment> by lazy {
        mutableListOf<BaseFragment>().apply {
            add(FragmentTools.newInstance(args1 = "FragmentTools", args2 = "0"))
            add(VideoFragment.newInstance("VideoFragment", "1"))
            add(MyFragment.newInstance("MyFragment", "2"))
            add(FriendFragment.newInstance("FriendFragment", "3"))
            add(AccountFragment.newInstance("AccountFragment", "4"))
        }

    }

    override fun initWindowAttributes() {
        setAllowFullScreen(false)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun bindLayout(layoutResId: Int) {
        setContentView(mBinding.root)
    }

    override fun initView() { // 开启状态栏沉浸
        setStatusBar(true)
        mBinding.includeToolbar.tvToolbarTitle.text = getString(R.string.app_name)
        initFragment()
    }

    /**
     * 设置状态栏颜色
     *
     * @return
     */
    override fun getStatusColor(): Int {
        return ResourceUtil.getInstance()
            .getColor(context, com.ethan.hydrogen.R.color.black)
    }

    override fun initListener() {
        mBinding.includeMainContent.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem: MenuItem ->
            replaceFragment(
                lastShowFragment, when (menuItem.itemId) {
                    R.id.nav_tools -> 0
                    R.id.nav_view  -> 1
                    R.id.nav_libs  -> 2
                    R.id.nav_mine  -> 3
                    else           -> 0
                }
            )
            true
        }
    }

    private var lastShowFragment = 0
    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .add(mBinding.includeMainContent.container.id, fragments[0])
            .show(fragments[0])
            .commitAllowingStateLoss()
    }

    private fun replaceFragment(lastShowFragment: Int, index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.hide(fragments[lastShowFragment])
        this.lastShowFragment = index
        if (fragments[index].isAdded.not()) {
            transaction.add(mBinding.includeMainContent.container.id, fragments[index])
        }
        transaction.show(fragments[index])
            .commitAllowingStateLoss()
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}