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
package com.ethan.hydrogen.demo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.util.setOnDebouncedItemClick
import com.ethan.hydrogen.demo.R
import com.ethan.hydrogen.demo.adapter.AdapterSimpleData
import com.ethan.hydrogen.demo.databinding.FragmentToolsBinding
import com.ethan.hydrogen.ui.BaseFragment
import com.ethan.hydrogen.utils.DateUtil
import com.ethan.hydrogen.utils.FileUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class FragmentTools : BaseFragment() {
    private val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)
    private val mBinding: FragmentToolsBinding by lazy { FragmentToolsBinding.inflate(layoutInflater) }
    private var args1: String? = null
    private var args2: String? = null
    private val mAdapter = AdapterSimpleData()

    companion object {
        private val TAG = FragmentTools::class.java.simpleName
        fun newInstance(args1: String?, args2: String?): FragmentTools {
            val args = Bundle()
            val fragment = FragmentTools()
            fragment.args1 = args1
            fragment.args2 = args2
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_tools

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup): View = mBinding.root

    override fun initView(view: View) {
        mBinding.recycler.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = mAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        requestData()
    }

    private fun requestData() {
        val data = FileUtil.getInstance().readAssetsFile(context, "tools.json")
        val itemList = Gson().fromJson<List<String>>(data, object : TypeToken<List<String>>() {}.type)
        mAdapter.addAll(itemList)
        mLogger.info("LOG:FragmentTools:requestData data={}", data)
    }


    override fun initListener() {
        super.initListener()

        mAdapter.setOnDebouncedItemClick(500L) { _, _, position ->
            mLogger.info("LOG:FragmentTools:initListener position={}", position)
            when (position) {
                0 -> {
                    printDateUtil()
                }
            }
        }
    }

    private fun printDateUtil() {

        mLogger.info("LOG:MainActivity:onCreate dateFormat={}", DateUtil.getCurrentDate())
        mLogger.info("LOG:MainActivity:onCreate formatDate={}", DateUtil.getInstance().formatDate(Date(), DateUtil.DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MIN_SECOND))
        mLogger.info("LOG:MainActivity:onCreate formatDate={}", DateUtil.getInstance().formatDate(System.currentTimeMillis(), DateUtil.DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MIN))
        mLogger.info("LOG:MainActivity:onCreate formatDate={}", DateUtil.getInstance().formatDate(System.currentTimeMillis(), DateUtil.DATE_FORMAT_YEAR_MONTH_DAY_HOUR))
        mLogger.info("LOG:MainActivity:onCreate formatDate={}", DateUtil.getInstance().formatDate(System.currentTimeMillis(), DateUtil.DATE_FORMAT_YEAR_MONTH_DAY_HOUR))
        mLogger.info("LOG:MainActivity:onCreate formatDate={}", DateUtil.getInstance().formatDate(System.currentTimeMillis(), DateUtil.DATE_FORMAT_YEAR_MONTH_DAY))
        mLogger.info("LOG:MainActivity:onCreate formatDate={}", DateUtil.getInstance().formatDate(System.currentTimeMillis(), DateUtil.DATE_FORMAT_YEAR_MONTH))
        mLogger.info("LOG:MainActivity:onCreate formatDate={}", DateUtil.getInstance().formatDate(System.currentTimeMillis(), DateUtil.DATE_FORMAT_MONTH_DAY))
        mLogger.info("LOG:MainActivity:onCreate formatDate={}", DateUtil.getInstance().formatDate(System.currentTimeMillis(), DateUtil.DATE_FORMAT_YEAR))
        mLogger.info("LOG:MainActivity:onCreate formatDate={}", DateUtil.getInstance().formatDate(System.currentTimeMillis(), DateUtil.DATE_FORMAT_MONTH))
        mLogger.info("LOG:MainActivity:onCreate formatDate={}", DateUtil.getInstance().formatDate(System.currentTimeMillis(), DateUtil.DATE_FORMAT_DAY))
        mLogger.info("LOG:MainActivity:onCreate formatDate={}", DateUtil.getInstance().formatDate(System.currentTimeMillis(), DateUtil.DATE_FORMAT_HOUR_MIN_SECOND))
        mLogger.info("LOG:MainActivity:onCreate formatDate={}", DateUtil.getInstance().formatDate(System.currentTimeMillis(), DateUtil.DATE_FORMAT_HOUR_MIN))
        mLogger.info("LOG:MainActivity:onCreate 日期对应的秒数 Second={}", DateUtil.convertToSeconds(Date()))
        mLogger.info("LOG:MainActivity:onCreate 日期对应的分钟数 Minutes={}", DateUtil.convertToMinutes(Date()))
        mLogger.info("LOG:MainActivity:onCreate 日期对应的小时数 Hours={}", DateUtil.convertToHours(Date()))
        mLogger.info("LOG:MainActivity:onCreate Year={}", DateUtil.getYear(Date())) //1661241420000
        mLogger.info("LOG:MainActivity:onCreate 2022-08-23T07:57:00.000+0000 转成毫秒={}", DateUtil.getInstance().parseDate("2022-08-23T07:57:00.000+0000", DateUtil.DATE_FORMAT_DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MIN_SECOND_SSSZ))
        mLogger.info("LOG:MainActivity:onCreate 1661241420000 格式化={}", DateUtil.getInstance().formatDate(1661241420000, DateUtil.DATE_FORMAT_DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MIN_SECOND_SSSZ))
        mLogger.info("LOG:MainActivity:onCreate 获取两个日期之间的日期集合={}", Gson().toJson(DateUtil.getInstance().findDates("2023-06-15", DateUtil.DATE_FORMAT_YEAR_MONTH_DAY, "2023-08-20", DateUtil.DATE_FORMAT_YEAR_MONTH_DAY, DateUtil.DATE_FORMAT_YEAR_MONTH_DAY)))
        mLogger.info("LOG:FragmentTools:printDateUtil prettyDuration={} ", DateUtil.getInstance().prettyDuration(1661241420000))
        mLogger.info("LOG:FragmentTools:printDateUtil prettyDuration={} ", DateUtil.getInstance().prettyDuration((3600*24*30+2340)*1000L))
        mLogger.info("LOG:FragmentTools:printDateUtil prettyDuration={} ", DateUtil.getInstance().prettyDuration(1800*1000))
        mLogger.info("LOG:FragmentTools:printDateUtil prettyDuration={} ", DateUtil.getInstance().prettyDuration(900*1000))
        mLogger.info("LOG:FragmentTools:printDateUtil prettyDuration={} ", DateUtil.getInstance().prettyDuration(450*1000))
        mLogger.info("LOG:FragmentTools:printDateUtil prettyDuration={} ", DateUtil.getInstance().prettyDuration(200*1000))
        mLogger.info("LOG:FragmentTools:printDateUtil getDateFromMillis={}", DateUtil.getInstance().getDateFromMillis(1661241420000))
        mLogger.info("LOG:FragmentTools:printDateUtil getDateTimeFromMillis={}", DateUtil.getInstance().getDateTimeFromMillis(1661241420000))

        mLogger.info("LOG:FragmentTools:printDateUtil getDateFormat={}", DateUtil.getInstance().getDateFormat(2023,9,23))
        mLogger.info("LOG:FragmentTools:printDateUtil getTimeFormat={}", DateUtil.getInstance().getTimeFormat(Date()))
        mLogger.info("LOG:FragmentTools:printDateUtil getIntervalDays={}", DateUtil.getInstance().getIntervalDays("2023-06-15", "2023-06-30"))

    }

}