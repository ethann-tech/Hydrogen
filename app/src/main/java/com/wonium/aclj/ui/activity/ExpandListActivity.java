/*
 * Copyright  2018.  wonium
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
 *
 */

package com.wonium.aclj.ui.activity;


import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.aclj.R;
import com.wonium.aclj.adapter.MyWorkAdapter;
import com.wonium.aclj.bean.Work;
import com.wonium.aclj.bean.WorkChild;
import com.wonium.aclj.bean.WorkGroup;
import com.wonium.aclj.databinding.ActivityExpandListBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.cicada.ui.BaseActivity;


import java.util.ArrayList;
import java.util.List;

@Route(path = PageRouter.ACTIVITY_EXPAND_LIST)
public class ExpandListActivity extends BaseActivity {
    private ExpandableListView mExpandableListView;
    private MyWorkAdapter mMyWorkAdapter;
    // 数据源
    private List<Work> mWorkList;
    private Work mWork;
    private WorkGroup mWorkGroup;
    private List<WorkChild> mWorkChildList;
    private WorkChild mWorkChild;
    private ActivityExpandListBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getStatusColor() {
        return getResources().getColor(R.color.black);
    }

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
        setScreenRotate(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_expand_list;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = DataBindingUtil.setContentView(this,layoutResId);
    }

    @Override
    public void initView() {
        setSupportActionBar(mBinding.includeExpandableToolbar.toolbar);
        mBinding.includeExpandableToolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mBinding.setTitle(getString(R.string.activity_expandable_list));
        // 初始化数据
        init();
        mExpandableListView = findViewById(R.id.expand);
        // 去除默认的指示器
        mExpandableListView.setGroupIndicator(null);
        mMyWorkAdapter = new MyWorkAdapter(getApplicationContext(), mWorkList);
        mExpandableListView.setAdapter(mMyWorkAdapter);
    }

    @Override
    public void initListener() {
        mBinding.includeExpandableToolbar.toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void init() {
        mWorkList = new ArrayList<>();
        // 后端开发
        mWork = new Work();
        // 表头
        mWorkGroup = new WorkGroup();
        mWorkGroup.setImage1Group(R.mipmap.app_launcher);
        mWorkGroup.setTitleGroup("后端开发");
        mWork.setmWorkGroup(mWorkGroup);
        // 表体
        mWorkChildList = new ArrayList<>();
        mWorkChild = new WorkChild();// Java
        mWorkChild.setImageChild(R.mipmap.app_launcher);
        mWorkChild.setTitleChild("Java");
        mWorkChildList.add(mWorkChild);
        mWorkChild = new WorkChild();// PHP
        mWorkChild.setImageChild(R.mipmap.app_launcher);
        mWorkChild.setTitleChild("PHP");
        mWorkChildList.add(mWorkChild);
        mWorkChild = new WorkChild();// Python
        mWorkChild.setImageChild(R.mipmap.app_launcher);
        mWorkChild.setTitleChild("Python");
        mWorkChildList.add(mWorkChild);
        mWorkChild = new WorkChild();// C#
        mWorkChild.setImageChild(R.mipmap.app_launcher);
        mWorkChild.setTitleChild("C#");
        mWorkChildList.add(mWorkChild);
        mWorkChild = new WorkChild();// C++
        mWorkChild.setImageChild(R.mipmap.app_launcher);
        mWorkChild.setTitleChild("C++");
        mWorkChildList.add(mWorkChild);
        mWorkChild = new WorkChild();// C
        mWorkChild.setImageChild(R.mipmap.app_launcher);
        mWorkChild.setTitleChild("C");
        mWorkChildList.add(mWorkChild);
        mWorkChild = new WorkChild();// 后端开发其他
        mWorkChild.setImageChild(R.mipmap.app_launcher);
        mWorkChild.setTitleChild("后端开发其他");
        mWorkChildList.add(mWorkChild);
        mWork.setmWorkChildList(mWorkChildList);
        mWorkList.add(mWork);
        // 移动开发
        mWork = new Work();
        mWorkGroup = new WorkGroup();
        mWorkGroup.setImage1Group(R.mipmap.app_launcher);
        mWorkGroup.setTitleGroup("移动开发");
        mWork.setmWorkGroup(mWorkGroup);
        mWorkChildList = new ArrayList<>();
        mWorkChild = new WorkChild();// IOS
        mWorkChild.setImageChild(R.mipmap.app_launcher);
        mWorkChild.setTitleChild("IOS");
        mWorkChildList.add(mWorkChild);
        mWorkChild = new WorkChild();// Android
        mWorkChild.setImageChild(R.mipmap.app_launcher);
        mWorkChild.setTitleChild("Android");
        mWorkChildList.add(mWorkChild);
        mWorkChild = new WorkChild();// WP
        mWorkChild.setImageChild(R.mipmap.app_launcher);
        mWorkChild.setTitleChild("WP");
        mWorkChildList.add(mWorkChild);
        mWorkChild = new WorkChild();// 移动开发其他
        mWorkChild.setImageChild(R.mipmap.app_launcher);
        mWorkChild.setTitleChild("移动开发其他");
        mWorkChildList.add(mWorkChild);
        mWork.setmWorkChildList(mWorkChildList);
        mWorkList.add(mWork);
    }
}
