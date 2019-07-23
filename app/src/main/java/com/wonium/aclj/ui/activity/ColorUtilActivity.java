package com.wonium.aclj.ui.activity;

import android.graphics.Color;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.aclj.R;
import com.wonium.hydrogen.adapter.ColorGradientatorAdapter;
import com.wonium.aclj.databinding.ActivityColorUtilBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.cicada.UniversalItemDecoration;
import com.wonium.cicada.ui.BaseActivity;
import com.wonium.cicada.utils.ColorUtil;

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
        mBinding = DataBindingUtil.setContentView(this, layoutResId);
        mBinding.setGradientColors("生成红，蓝亮色之间的过渡色，默认30种颜色，首位相接");
        mBinding.setNextColorByCurrentColor("根据当前显色显示下一个颜色");
    }

    @Override
    public void initView() {
        mBinding.setTitle(getResources().getString(R.string.tools_color));
        setSupportActionBar(mBinding.includeToolbarColor.toolbar);
        mBinding.includeToolbarColor.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mBinding.includeToolbarColor.toolbar.setNavigationOnClickListener(v -> finish());
        displayTestCase();
    }

    @Override
    public void initListener() {
        if (adapter != null) {
            adapter.setOnItemClickListener((view, position) -> {
                mBinding.setCurrentColor(adapter.getData(position));
                mBinding.setNextColor(ColorUtil.getInstance().getNextClorByCurrentColor(adapter.getData(position), gradientColors));
            });
        }
    }

    private void displayTestCase() {
        mBinding.tvColorUtilContent.setText("生成红，蓝两个颜色之间的过渡颜色\n ColorUtil.getInstance().getTransitionalColorsBetweenTwoColors(Color.RED,Color.BLUE);");
        mBinding.imgGradientator.setBackgroundColor(ColorUtil.getInstance().getTransitionalColorsBetweenTwoColors(Color.RED, Color.BLUE));
        Integer[] baseColors = new Integer[]{Color.RED, Color.BLUE};
        gradientColors = ColorUtil.getInstance().getGradientColors(baseColors);

        List<Integer> gradientColorsList = Arrays.stream(gradientColors).boxed().collect(Collectors.toList());
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
