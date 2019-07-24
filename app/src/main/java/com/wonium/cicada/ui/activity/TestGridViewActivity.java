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


import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.cicada.R;
import com.wonium.cicada.adapter.TestGridViewAdapter;
import com.wonium.cicada.databinding.ActivityTestGridBinding;
import com.wonium.cicada.router.PageRouter;
import com.wonium.hydrogen.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName: TestGridViewActivity
 * @Description: 描述一下
 * @Author: Ethan
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2019/3/27 21:34
 * @UpdateUser: update user
 * @UpdateDate: 2019/3/27 21:34
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
@Route(path = PageRouter.TEST_GRID_VIEW)
public class TestGridViewActivity extends BaseActivity {
    private ActivityTestGridBinding mBinding;
    private TestGridViewAdapter adapter;

    @Override
    protected int getStatusColor() {
        return getResources().getColor(R.color.black);
    }


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
         adapter =new TestGridViewAdapter();
        adapter.setDatas(buildData());
        mBinding.list.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        mBinding.list.setOnItemClickListener((parent, view, position, id) -> adapter.setSelectPosition(position));
    }


    private List<String> buildData(){
        List<String> datas =new ArrayList<>() ;
        for (int i=0;i<50;i++){
            datas.add("item"+i);
        }
        return datas;
    }

}
