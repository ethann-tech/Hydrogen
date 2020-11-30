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

package com.wonium.cicada.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wonium.cicada.R;
import com.wonium.cicada.adapter.MyAdapter;
import com.wonium.cicada.databinding.FragmentMyBinding;
import com.wonium.cicada.router.PageRouter;
import com.wonium.hydrogen.UniversalItemDecoration;
import com.wonium.hydrogen.ui.BaseFragment;
import com.wonium.hydrogen.utils.ToastUtil;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MyFragment extends BaseFragment {
    private FragmentMyBinding mBinding;
    private String args1;
    private String args2;
    private MyAdapter mAdapter;

    public static MyFragment newInstance(String args1, String args2) {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.args1 = args1;
        fragment.args1 = args2;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_my;
    }

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {
        mBinding = FragmentMyBinding.inflate(inflater);
        return mBinding.getRoot();
    }

    @Override
    protected void initView(View view) {
        String[] datas = getResources().getStringArray(R.array.my_recycler_view_data);
        List<String> list = Arrays.asList(datas);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mBinding.myRecyclerView.setLayoutManager(manager);
        mBinding.myRecyclerView.addItemDecoration(new UniversalItemDecoration() {
            @Override
            public Decoration getItemOffsets(int position) {
                ColorDecoration decoration = new ColorDecoration();
                decoration.bottom = 2;
                decoration.decorationColor = getResources().getColor(R.color.black_alpha_50);
                return decoration;
            }
        });
        mAdapter = new MyAdapter(getContext());
        mBinding.myRecyclerView.setAdapter(mAdapter);
        mAdapter.setDatas(list);

    }

    @Override
    protected void initListener() {
        super.initListener();
        mAdapter.setOnItemClickListener((view, position) -> goPageByPosition(position));
        mAdapter.setOnItemLongClickListener((view, position) -> ToastUtil.getInstance().show(getContext(),String.format(Locale.CHINA,"%d",position)));
    }

    /**
     * 根据点击的position 跳转到对应的页面
     *
     * @param position 索引值
     */
    private void goPageByPosition(int position) {
        switch (position) {
            case 0:
                ARouter.getInstance().build(PageRouter.MY_TOOLS_ACTIVITY).navigation(getContext());
                break;
            case 1:
                ARouter.getInstance().build(PageRouter.ACTIVITY_CUSTOM_COMPONENT).navigation(getContext());
                break;
            case 2:
                break;
            default:
                break;
        }
    }
}
