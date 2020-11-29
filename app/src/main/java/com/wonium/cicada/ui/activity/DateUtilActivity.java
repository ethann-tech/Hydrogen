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


import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.cicada.R;
import com.wonium.cicada.databinding.ActivityDateUtilBinding;
import com.wonium.cicada.router.PageRouter;
import com.wonium.cicada.base.BaseActivity;
import com.wonium.hydrogen.utils.DateUtil;

@Route(path = PageRouter.ACTIVITY_DATE_UTIL)
public class DateUtilActivity extends BaseActivity {
    private ActivityDateUtilBinding mBinding;

    @Override
    public void initListener() {
        mBinding.includeDateUtilToolbar.toolbar.setNavigationOnClickListener(v -> finish());
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
        return R.layout.activity_date_util;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = ActivityDateUtilBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    public void initView() {
        setSupportActionBar(mBinding.includeDateUtilToolbar.toolbar);
        mBinding.includeDateUtilToolbar.tvToolbarTitle.setText(getString(R.string.tools_date_util));
        mBinding.includeDateUtilToolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        displayTestCase();
    }


    private void displayTestCase() {
        StringBuilder builder = new StringBuilder();
        builder.append("使用方法：").append("\n").append("函数：\n").append(" String getDateTimeFromMillis(long timeInMillis)");
        builder.append("\n返回时间间隔的常用表示 例如：3天前、3分钟前、刚刚等等：");
        builder.append(DateUtil.getInstance().getTimeIntervalIntro(System.currentTimeMillis() - 30 * 3600 * 1000, false));
        builder.append("\n\n获得当天0点时间,单位毫秒数\nDateUtil.getInstance().getTimesMorning()\n\n");
        builder.append(DateUtil.getInstance().getTimesMorning());

        builder.append("\n\n获得当天24点时间,单位毫秒数\nDateUtil.getInstance().getTimesNight()\n\n");
        builder.append(DateUtil.getInstance().getTimesNight());

        builder.append("\n\n获得本周一0点时间,单位毫秒数\nDateUtil.getInstance().getTimesNight()\n\n");
        builder.append(DateUtil.getInstance().getTimesWeekMorning());

        builder.append("\n\n获得本周日24点时间,单位毫秒数\nDateUtil.getInstance().getTimesWeekNight()\n\n");
        builder.append(DateUtil.getInstance().getTimesWeekNight());

        builder.append("\n\n获得本月第一天0点时间,单位毫秒数\nDateUtil.getInstance().getTimesMonthMorning()\n\n");
        builder.append(DateUtil.getInstance().getTimesMonthMorning());

        builder.append("\n\n获得本月最后一天24点时间,单位毫秒数\nDateUtil.getInstance().getTimesMonthNight()\n\n");
        builder.append(DateUtil.getInstance().getTimesMonthNight());

        mBinding.tvContent.setText(builder.toString());
    }

}
