package com.wonium.aclj.ui.activity;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.aclj.R;
import com.wonium.aclj.adapter.MyAdapter;
import com.wonium.aclj.databinding.ActivityMyToolsBinding;
import com.wonium.aclj.presenter.MainPresenter;
import com.wonium.aclj.presenter.impl.MainPresenterImpl;
import com.wonium.aclj.router.PageRouter;
import com.wonium.aclj.ui.view.MainView;
import com.wonium.cicada.UniversalItemDecoration;
import com.wonium.cicada.ui.BaseActivity;

import java.util.List;

@Route(path = PageRouter.MY_TOOLS_ACTIVITY)
public class MyToolsActivity extends BaseActivity implements MainView {
    private ActivityMyToolsBinding mBinding;
    private MyAdapter myAdapter;
    private MainPresenter mPresenter;
    @Override
    protected int getStatusColor() {
        return getResources().getColor(R.color.black);
    }

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
        setScreenRoate(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_my_tools;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = DataBindingUtil.setContentView(this, layoutResId);
    }

    @Override
    public void initView() {
        mPresenter =new MainPresenterImpl(this);

        setStatusBar(true);
        setSupportActionBar(mBinding.includeToolsToolbar.toolbar);
        mBinding.includeToolsToolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mBinding.setTitle(getResources().getString(R.string.activity_tools));
        LinearLayoutManager manager =new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mBinding.includeToolsRecycler.viewRecycler.setLayoutManager(manager);
        mBinding.includeToolsRecycler.viewRecycler.addItemDecoration(new UniversalItemDecoration() {
            @Override
            public Decoration getItemOffsets(int position) {
                ColorDecoration colorDecoration =new ColorDecoration();
                colorDecoration.bottom=2;
                colorDecoration.decorationColor=getContext().getResources().getColor(R.color.lightGray);
                return colorDecoration;
            }
        });
        myAdapter =new MyAdapter(getContext());
        mBinding.includeToolsRecycler.viewRecycler.setAdapter(myAdapter);

        mPresenter.getListData(getContext());
    }

    @Override
    public void initListener() {
        mBinding.includeToolsToolbar.toolbar.setNavigationOnClickListener(v -> finish());

    }

    @Override
    public void updateListData(List<String> datas) {
        myAdapter.setDatas(datas);
    }
}
