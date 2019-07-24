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
import com.orhanobut.logger.Logger;
import com.wonium.cicada.R;
import com.wonium.cicada.databinding.ActivityByteUtilBinding;
import com.wonium.cicada.router.PageRouter;
import com.wonium.hydrogen.ui.BaseActivity;
import com.wonium.hydrogen.utils.ByteUtil;

import java.nio.ByteOrder;

/**
 * @ClassName: ByteActivity
 * @Description: 添加描述
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/11/29 15:00
 * @UpdateUser: 添加更新者
 * @UpdateDate: 2018/11/29 15:00
 * @UpdateDescription: 更新描述 二进制
 * @Version:
 */
@Route(path = PageRouter.ACTIVITY_BYTE_UTIL)
public class ByteUtilActivity extends BaseActivity {
    private ActivityByteUtilBinding mBinding;

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
        return R.layout.activity_byte_util;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = DataBindingUtil.setContentView(this, layoutResId);
    }

    @Override
    public void initView() {

        mBinding.setTitle(getString(R.string.tools_byte));
        setSupportActionBar(mBinding.includeToolbarByte.toolbar);
        mBinding.includeToolbarByte.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_24dp));
        // getBitValue
        byte value = (byte) 234;
        Logger.d("234 convert binary" + ByteUtil.getInstance().byteToBit(value));
        Logger.d("获取value index 为4的值" + ByteUtil.getInstance().getBitValue(value, 4));

        //byteArrayToShort 如果你不了解大小端 请参考 https://blog.wonium.com/archives/115/
        byte[] bytes = new byte[2];
        bytes[0] = 8;
        bytes[1] = 1;
        Logger.d("bytesArrayToShort Big-->" + ByteUtil.getInstance().byteArrayToShort(bytes, ByteOrder.BIG_ENDIAN));
        Logger.d("byteArrayToShort--Little>" + ByteUtil.getInstance().byteArrayToShort(bytes, ByteOrder.LITTLE_ENDIAN));

        //printByteArrayToBinary 如果你不了解大小端 请参考 https://blog.wonium.com/archives/115/
        Logger.d("shortToByteArray Big-Endian-->" + ByteUtil.getInstance().printByteArrayToBinary(ByteUtil.getInstance().shortToByteArray((short) 2049, ByteOrder.BIG_ENDIAN)));
        Logger.d("shortToByteArray Little-Endian-->" + ByteUtil.getInstance().printByteArrayToBinary(ByteUtil.getInstance().shortToByteArray((short) 2049, ByteOrder.LITTLE_ENDIAN)));


        // byteArrayToInt
        byte[] byteInt = new byte[4];
        byteInt[0] = 5;
        byteInt[1] = 1;
        byteInt[2] = 1;
        byteInt[3] = 64;
        Logger.d("byteArrayToInt Big-Endian-->" + ByteUtil.getInstance().byteArrayToInt(byteInt, ByteOrder.BIG_ENDIAN));
        Logger.d("byteArrayToInt Little-Endian-->" + ByteUtil.getInstance().byteArrayToInt(byteInt, ByteOrder.LITTLE_ENDIAN));

        // intToByteArray 83951936
        Logger.d("intToByteArray Big-Endian-->" + ByteUtil.getInstance().printByteArrayToBinary(ByteUtil.getInstance().intToByteArray(83951936, ByteOrder.BIG_ENDIAN)));
        Logger.d("intToByteArray Little-Endian-->" + ByteUtil.getInstance().printByteArrayToBinary(ByteUtil.getInstance().intToByteArray(83951936, ByteOrder.LITTLE_ENDIAN)));

        // byteArrayToFloat
        byte[] byteFloat = new byte[4];
        byteFloat[0] = 1;
        byteFloat[1] = 0;
        byteFloat[2] = 0;
        byteFloat[3] = 0;
        Logger.d("byteArrayToFloat Big-Endian-->" + ByteUtil.getInstance().byteArrayToFloat(byteFloat, ByteOrder.BIG_ENDIAN));
        Logger.d("byteArrayToFloat Little-Endian-->" + ByteUtil.getInstance().byteArrayToFloat(byteFloat, ByteOrder.LITTLE_ENDIAN));
    }

    @Override
    public void initListener() {
        mBinding.includeToolbarByte.toolbar.setNavigationOnClickListener(v -> finish());
    }
}
