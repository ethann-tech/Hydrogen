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

import android.graphics.Color;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.cicada.R;
import com.wonium.cicada.adapter.ColorGradientatorAdapter;
import com.wonium.cicada.databinding.ActivityColorUtilBinding;
import com.wonium.cicada.router.PageRouter;
import com.wonium.hydrogen.UniversalItemDecoration;
import com.wonium.cicada.base.BaseActivity;
import com.wonium.hydrogen.utils.ColorUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Route(path = PageRouter.ACTIVITY_COLOR_UTIL)
public class ColorUtilActivity extends BaseActivity {
    private ActivityColorUtilBinding mBinding;
    private ColorGradientatorAdapter adapter;
    private int[] gradientColors;

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
        return R.layout.activity_color_util;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = ActivityColorUtilBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mBinding.tvGradientColors.setText("生成红，蓝亮色之间的过渡色，默认30种颜色，首位相接");
        mBinding.tvNextColorByCurrentColor.setText("根据当前显色显示下一个颜色");
    }

    @Override
    public void initView() {
        mBinding.includeToolbarColor.tvToolbarTitle.setText(getResources().getString(R.string.tools_color));
        setSupportActionBar(mBinding.includeToolbarColor.toolbar);
        mBinding.includeToolbarColor.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mBinding.includeToolbarColor.toolbar.setNavigationOnClickListener(v -> finish());
        displayTestCase();
    }

    @Override
    public void initListener() {
        if (adapter != null) {
            adapter.setOnItemClickListener((view, position) -> {
                mBinding.imgCurrentColor.setImageResource(adapter.getData(position));
                mBinding.tvNextColorByCurrentColor.setText(ColorUtil.getInstance().getNextClorByCurrentColor(adapter.getData(position), gradientColors));
            });
        }
    }

    private void displayTestCase() {
        mBinding.tvColorUtilContent.setText("生成红，蓝两个颜色之间的过渡颜色\n ColorUtil.getInstance().getTransitionalColorsBetweenTwoColors(Color.RED,Color.BLUE);");
        mBinding.imgGradientator.setBackgroundColor(ColorUtil.getInstance().getTransitionalColorsBetweenTwoColors(Color.RED, Color.BLUE));
        Integer[] baseColors = new Integer[]{Color.RED, Color.BLUE};
        gradientColors = ColorUtil.getInstance().getGradientColors(baseColors);

        List<Integer> gradientColorsList = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            gradientColorsList = Arrays.stream(gradientColors).boxed().collect(Collectors.toList());
        }
        adapter = new ColorGradientatorAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        mBinding.includeColorRecycler.viewRecycler.setLayoutManager(manager);
        mBinding.includeColorRecycler.viewRecycler.addItemDecoration(new UniversalItemDecoration() {
            @Override
            public Decoration getItemOffsets(int position) {
                ColorDecoration colorDecoration = new ColorDecoration();
                colorDecoration.right = 2;
                colorDecoration.decorationColor = getContext().getResources().getColor(R.color.lightGray);
                return colorDecoration;
            }
        });

        mBinding.includeColorRecycler.viewRecycler.setAdapter(adapter);
        adapter.setDatas(gradientColorsList);


    }
}
