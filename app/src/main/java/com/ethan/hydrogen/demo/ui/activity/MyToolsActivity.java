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


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ethan.hydrogen.demo.R;
import com.ethan.hydrogen.demo.adapter.MyAdapter;
import com.ethan.hydrogen.demo.databinding.ActivityMyToolsBinding;
import com.ethan.hydrogen.demo.presenter.MainPresenter;
import com.ethan.hydrogen.demo.presenter.impl.MainPresenterImpl;
import com.ethan.hydrogen.demo.router.PageRouter;
import com.ethan.hydrogen.demo.ui.view.MainView;
import com.ethan.hydrogen.UniversalItemDecoration;
import com.ethan.hydrogen.demo.base.BaseActivity;

import java.util.List;

@Route(path = PageRouter.MY_TOOLS_ACTIVITY)
public class MyToolsActivity extends BaseActivity implements MainView {
    private ActivityMyToolsBinding mBinding;
    private MyAdapter myAdapter;
    private MainPresenter mPresenter;

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
        setScreenRotate(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_my_tools;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = ActivityMyToolsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    public void initView() {
        mPresenter = new MainPresenterImpl(this);
        setStatusBar(true);
        setSupportActionBar(mBinding.includeToolsToolbar.toolbar);
        mBinding.includeToolsToolbar.toolbar.setNavigationIcon(com.ethan.hydrogen.R.drawable.ic_arrow_back_24dp);
        mBinding.includeToolsToolbar.tvToolbarTitle.setText(getResources().getString(R.string.activity_tools));
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mBinding.includeToolsRecycler.viewRecycler.setLayoutManager(manager);
        mBinding.includeToolsRecycler.viewRecycler.addItemDecoration(new UniversalItemDecoration() {
            @Override
            public Decoration getItemOffsets(int position) {
                ColorDecoration colorDecoration = new ColorDecoration();
                colorDecoration.bottom = 2;
                colorDecoration.decorationColor = getContext().getResources().getColor(com.ethan.hydrogen.R.color.lightGray);
                return colorDecoration;
            }
        });
        myAdapter = new MyAdapter(getContext());
        mBinding.includeToolsRecycler.viewRecycler.setAdapter(myAdapter);
        mPresenter.getListData(getContext());
    }

    @Override
    public void initListener() {
        mBinding.includeToolsToolbar.toolbar.setNavigationOnClickListener(v -> finish());
        myAdapter.setOnItemClickListener((view, position) -> {
            switch (position) {
                case 0:
                    ARouter.getInstance().build(PageRouter.ACTIVITY_MANAGER_ACTIVITY).navigation(getContext());
                    break;
                case 1:
                    ARouter.getInstance().build(PageRouter.ACTIVITY_BITMAP_UTIL).navigation(getContext());
                    break;
                case 2:
                    ARouter.getInstance().build(PageRouter.ACTIVITY_BYTE_UTIL).navigation(getContext());
                    break;
                case 3:
                    ARouter.getInstance().build(PageRouter.ACTIVITY_COLOR_UTIL).navigation(getContext());
                    break;
                case 4:
                    ARouter.getInstance().build(PageRouter.ACTIVITY_DATA_CLEAR).navigation(getContext());
                    break;
                case 5:
                    ARouter.getInstance().build(PageRouter.ACTIVITY_DATE_UTIL).navigation(getContext());
                    break;
                case 6:
                    ARouter.getInstance().build(PageRouter.ACTIVITY_DEVICE_UTIL).navigation(getContext());
                    break;
                case 7:
                    ARouter.getInstance().build(PageRouter.ACTIVITY_DENSITY_UTIL).navigation(getContext());
                    break;
                case 8:
                    ARouter.getInstance().build(PageRouter.ACTIVITY_FILE_UTIL).navigation(getContext());
                case 9:
                    ARouter.getInstance().build(PageRouter.ACTIVITY_INPUT_METHOD_MANAGER_UTIL).navigation(getContext());
                    break;
                case 10:
                    ARouter.getInstance().build(PageRouter.ACTIVITY_INTENT_UTIL).navigation(getContext());
                    break;
                case 11:
                    ARouter.getInstance().build(PageRouter.ACTIVITY_MD5_UTIL).navigation(getContext());
                    break;
                case 15:
                    ARouter.getInstance().build(PageRouter.ACTIVITY_STRING_UTIL).navigation(getContext());
                    break;
                default:

                    break;
            }
        });
    }

    @Override
    public void updateListData(List<String> datas) {
        myAdapter.setDatas(datas);
    }
}
