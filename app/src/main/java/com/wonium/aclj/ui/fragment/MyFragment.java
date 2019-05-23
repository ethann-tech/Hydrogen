package com.wonium.aclj.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wonium.aclj.R;
import com.wonium.aclj.adapter.MyAdapter;
import com.wonium.aclj.databinding.FragmentMyBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.cicada.UniversalItemDecoration;
import com.wonium.cicada.ui.BaseFragment;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

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
        mBinding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false);
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
                break;
            case 2:
                break;
            default:
                break;
        }
    }
}
