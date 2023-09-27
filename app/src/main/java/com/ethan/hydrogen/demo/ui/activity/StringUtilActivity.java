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




import com.alibaba.android.arouter.facade.annotation.Route;
import com.ethan.hydrogen.demo.databinding.ActivityStringUtilBinding;
import com.ethan.hydrogen.utils.StringUtil;
import com.ethan.hydrogen.demo.R;
import com.ethan.hydrogen.demo.presenter.StringPresenter;
import com.ethan.hydrogen.demo.presenter.impl.StringPresenterImpl;
import com.ethan.hydrogen.demo.router.PageRouter;
import com.ethan.hydrogen.demo.ui.view.StringView;
import com.ethan.hydrogen.demo.base.BaseActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName: StringUtilActivity.java
 * @Description: 类描述
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/11/17 15:27
 * @UpdateUser: update user
 * @UpdateDate: 2018/11/17 15:27
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
@Route(path = PageRouter.ACTIVITY_STRING_UTIL)
public class StringUtilActivity extends BaseActivity implements StringView {
    private ActivityStringUtilBinding mBinding;
    private StringPresenter mPresenter;
    private StringBuilder builder = new StringBuilder();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void initListener() {
        mBinding.btnIsNull.setOnClickListener(v -> mPresenter.isNull(mBinding.editIsNull.getText().toString().trim()));
        mBinding.btnIsEmpty.setOnClickListener(v -> mPresenter.isEmpty(mBinding.editIsEmpty.getText().toString().trim()));
        mBinding.btnValueOf.setOnClickListener(v -> mPresenter.valueOf(mBinding.editValueOf.getText().toString().trim()));
        mBinding.btnGetStringFromMap.setOnClickListener(v -> mPresenter.getStringFormMap());
        mBinding.includeToolbar.toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected int getStatusColor() {
        return getContext().getResources().getColor(com.ethan.hydrogen.R.color.black);
    }

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
        setScreenRotate(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_string_util;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = ActivityStringUtilBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    public void initView() {
        setSupportActionBar(mBinding.includeToolbar.toolbar);
        mBinding.includeToolbar.tvToolbarTitle.setText("StringUtil");
        mBinding.includeToolbar.toolbar.setNavigationIcon(com.ethan.hydrogen.R.drawable.ic_arrow_back_24dp);
        mPresenter = new StringPresenterImpl(this);
        try {
            builder.append("字符串重置 wonium-->").append(StringUtil.getInstance().reverseString("wonium")).append("\n")
                   .append("格式化10以内的整数 例如 5-->").append(StringUtil.getInstance().formatInt(5)).append("\n")
                   .append("更改字符集 例如 wonium-->").append(StringUtil.getInstance().changeCharSet("wonium", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("LOG:StringUtilActivity:initView ={}", "更改字符集方法需要处理 UnsupportedEncodingException异常");

        }

        mBinding.tvExample.setText(StringUtil.getInstance().isEmpty(builder.toString()));
    }

    @Override
    public void showTestIsNullMessage(String message) {
        mBinding.tvResultIsNull.setText(message);
    }

    @Override
    public void showTestIsEmptyMessage(String message) {
        mBinding.tvIsEmpty.setText(message);
    }

    @Override
    public void showTestValueOfMessage(String message) {
        mBinding.tvValueOf.setText(message);
    }

    @Override
    public void showTestGetStringFromMap(String message) {
        mBinding.tvStringForMap.setText(message);
    }
}
