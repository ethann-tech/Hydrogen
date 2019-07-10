/*
 * Copyright  2018  WoNium, Joy, Lokiwife.
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

package com.wonium.aclj.ui.activity;



import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.orhanobut.logger.Logger;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivityStringUtilBinding;
import com.wonium.aclj.presenter.StringPresenter;
import com.wonium.aclj.presenter.impl.StringPresenterImpl;
import com.wonium.aclj.router.PageRouter;
import com.wonium.aclj.ui.view.StringView;
import com.wonium.cicada.ui.BaseActivity;
import com.wonium.cicada.utils.StringUtil;

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
        return getContext().getResources().getColor(R.color.black);
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
        mBinding = DataBindingUtil.setContentView(this, layoutResId);
    }

    @Override
    public void initView() {
        setSupportActionBar(mBinding.includeToolbar.toolbar);
        mBinding.setToolbarTitle("StringUtil");
        mBinding.includeToolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mPresenter = new StringPresenterImpl(this);
        try {
            builder.append("字符串重置 wonium-->").append(StringUtil.getInstance().reverseString("wonium")).append("\n")
                    .append("格式化10以内的整数 例如 5-->").append(StringUtil.getInstance().formatInt(5)).append("\n")
                    .append("更改字符集 例如 wonium-->").append(StringUtil.getInstance().changeCharSet("wonium", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Logger.e("更改字符集方法需要处理 UnsupportedEncodingException异常");
        }

        mBinding.tvExample.setText(StringUtil.getInstance().isEmpty(builder.toString()));
    }

    @Override
    public void showTestIsNullMessage(String message) {
        mBinding.setIsNull(message);
    }

    @Override
    public void showTestIsEmptyMessage(String message) {
        mBinding.setIsEmpty(message);
    }

    @Override
    public void showTestValueOfMessage(String message) {
        mBinding.setValueOf(message);
    }

    @Override
    public void showTestGetStringFromMap(String message) {
        mBinding.setGetStringFromMap(message);
    }
}
