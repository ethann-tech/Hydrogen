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

import android.graphics.Bitmap;

import com.ethan.hydrogen.demo.R;
import com.ethan.hydrogen.demo.base.BaseActivity;
import com.ethan.hydrogen.demo.databinding.ActivityBitmapUtilBinding;
import com.ethan.hydrogen.demo.router.PageRouter;
import com.ethan.hydrogen.utils.BitmapUtil;
import com.ethan.hydrogen.utils.ByteUtil;
import com.ethan.hydrogen.utils.ThreadPoolUtil;
import com.ethan.hydrogen.utils.ToastUtil;

import zlc.season.butterfly.annotation.Agile;



@Agile(scheme = PageRouter.ACTIVITY_BITMAP_UTIL)
public class BitmapUtilActivity extends BaseActivity {
    private ActivityBitmapUtilBinding mBinding;

    @Override
    public void initListener() {
        mBinding.includeToolbarBitmap.toolbar.setNavigationOnClickListener(v -> finish());
        // 创建一个bitmap
        mBinding.btnCreateBitmap.setOnClickListener(v -> createBitmap());
        // img 转bitmap
        mBinding.btnImgToBitmap.setOnClickListener(v -> imgToBitmap());
        // 获取Bitmap的大小
        mBinding.btnBitmapSize.setOnClickListener(v -> getBitmapSize());
        // Bitmap To Bytes
        mBinding.btnBitmapToBytes.setOnClickListener(v -> bitmapToBytes());

        mBinding.btnGetBitmapByPath.setOnClickListener(v -> getBitmapFromPath());

    }

    /**
     * 创建一个Bitmap
     */
    private void createBitmap() {
        // 显示创建的Bitmap
        mBinding.imgShowCreateBitmap.setImageBitmap(BitmapUtil.getInstance().createBitmap(192, 192, getResources().getColor(com.ethan.hydrogen.R.color.darkSalmon)));

    }

    private void imgToBitmap() {
        ToastUtil.getInstance().show(getContext(), "imgToBitmap");
        mBinding.imgToBitmap.setImageBitmap(BitmapUtil.getInstance().imgToBitmap(getContext(), R.mipmap.img_wonium));
    }

    private void getBitmapSize() {
        Bitmap bitmap = BitmapUtil.getInstance().imgToBitmap(getContext(), R.mipmap.img_wonium);
        mBinding.imgToBitmap.setImageBitmap(bitmap);
        mBinding.tvBitmapSize.setText(new StringBuilder().append("蜗牛图片转换成Bitmap的Size:\n").append(BitmapUtil.getInstance().getBitmapSize(bitmap)));
    }

    private void bitmapToBytes() {
        ToastUtil.getInstance().show(getContext(), "查看Log");
        Bitmap bitmap = BitmapUtil.getInstance().imgToBitmap(getContext(), R.mipmap.img_wonium);
        logger.debug(ByteUtil.getInstance().printByteArrayToBinary(BitmapUtil.getInstance().bitmapToByte(bitmap)));
    }

    private void getBitmapFromPath() {
        String path = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542904680889&di=f436ad37f1dd8cacdac4c4ea138f685d&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D8987b62f0055b31988f48a362bc0e853%2Feac4b74543a98226e9b8d1098082b9014a90eba7.jpg";
        ThreadPoolUtil.INSTANCE.execute(() -> {
            Bitmap bitmap = BitmapUtil.getInstance().getBitmapByPath(path);
            if(bitmap != null) {
                runOnUiThread(() -> mBinding.imgDisplayBitmapFormPath.setImageBitmap(bitmap));
            }
        });
    }

    /**
     * 图片反转
     */
    private void flippingBitmap() {
        Bitmap bitmap = BitmapUtil.getInstance().imgToBitmap(getContext(), R.mipmap.app_launcher);
        mBinding.btnFlippingBitmap.setOnClickListener(v -> {
            mBinding.imgFlippingBitmap.setImageBitmap(BitmapUtil.getInstance().flippingBitmap(bitmap));
        });
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
        return R.layout.activity_bitmap_util;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = ActivityBitmapUtilBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    public void initView() {
        setStatusBar(true);
        setSupportActionBar(mBinding.includeToolbarBitmap.toolbar);
        mBinding.includeToolbarBitmap.toolbar.setNavigationIcon(com.ethan.hydrogen.R.drawable.ic_arrow_back_24dp);
        mBinding.includeToolbarBitmap.tvToolbarTitle.setText(getResources().getString(R.string.tools_bitmap));
        flippingBitmap();

        //图片旋转
        Bitmap bitmap = BitmapUtil.getInstance().imgToBitmap(getContext(), R.mipmap.app_launcher);
        mBinding.imgRotateBitmap.setImageBitmap(BitmapUtil.getInstance().rotateBitmap(bitmap, -90));
        // 打印RGBA数据
        byte[] bytes = BitmapUtil.getInstance().getPixels(bitmap);
        logger.debug("打印RGBA数据————>: " + ByteUtil.getInstance().printByteArrayToBinary(bytes));
        // 打印RGB数据 24位
        byte[] bytes24 = BitmapUtil.getInstance().getRGBDataFormat24(bitmap);
        logger.debug("打印RGB数据24位————>: " + ByteUtil.getInstance().printByteArrayToBinary(bytes24));
    }
}
