package com.ethan.hydrogen.demo.ui.activity

import com.ethan.hydrogen.demo.R
import com.ethan.hydrogen.demo.base.BaseActivity
import com.ethan.hydrogen.demo.databinding.ActivityDeviceUtilBinding
import zlc.season.butterfly.annotation.Agile

@Agile(scheme = "/")
class ActivityDeviceUtil :BaseActivity(){
    private val mBinding :ActivityDeviceUtilBinding by lazy {
        ActivityDeviceUtilBinding.inflate(layoutInflater)
    }
    override fun initWindowAttributes() {
        setAllowFullScreen(false)
    }

    override fun getLayoutResId(): Int =R.layout.activity_device_util

    override fun initView() {
       setContentView(mBinding.root)
    }

    override fun initListener() {

    }

}