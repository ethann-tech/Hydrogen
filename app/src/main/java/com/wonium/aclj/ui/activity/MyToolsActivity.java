package com.wonium.aclj.ui.activity;


import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
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
        setScreenRotate(false);
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
        mPresenter = new MainPresenterImpl(this);
        setStatusBar(true);
        setSupportActionBar(mBinding.includeToolsToolbar.toolbar);
        mBinding.includeToolsToolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mBinding.setTitle(getResources().getString(R.string.activity_tools));
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mBinding.includeToolsRecycler.viewRecycler.setLayoutManager(manager);
        mBinding.includeToolsRecycler.viewRecycler.addItemDecoration(new UniversalItemDecoration() {
            @Override
            public Decoration getItemOffsets(int position) {
                ColorDecoration colorDecoration = new ColorDecoration();
                colorDecoration.bottom = 2;
                colorDecoration.decorationColor = getContext().getResources().getColor(R.color.lightGray);
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
                case 8:
                    ARouter.getInstance().build(PageRouter.ACTIVITY_FILE_UTIL).navigation(getContext());
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
