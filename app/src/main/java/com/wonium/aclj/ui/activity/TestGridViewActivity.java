package com.wonium.aclj.ui.activity;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.aclj.R;
import com.wonium.aclj.adapter.TestGridViewAdapter;
import com.wonium.aclj.databinding.ActivityTestGridBinding;
import com.wonium.aclj.router.PageRouter;
import com.wonium.aclj.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;

@Route(path = PageRouter.TEST_GRID_VIEW)
public class TestGridViewActivity extends BaseActivity {
    private ActivityTestGridBinding mBinding;
    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_test_grid;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = DataBindingUtil.setContentView(this,layoutResId);

    }

    @Override
    public void initView() {
        TestGridViewAdapter adapter =new TestGridViewAdapter();
        adapter.setDatas(buildData());
        mBinding.list.setAdapter(adapter);
    }

    @Override
    public void initListener() {

    }


    private List<String> buildData(){
        List<String> datas =new ArrayList<>() ;
        for (int i=0;i<50;i++){
            datas.add("item"+i);
        }
        return datas;
    }

}
