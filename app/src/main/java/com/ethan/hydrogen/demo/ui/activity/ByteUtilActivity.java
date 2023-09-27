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
import com.ethan.hydrogen.demo.R;
import com.ethan.hydrogen.demo.databinding.ActivityByteUtilBinding;
import com.ethan.hydrogen.utils.ByteUtil;
import com.ethan.hydrogen.demo.router.PageRouter;
import com.ethan.hydrogen.demo.base.BaseActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
        return R.layout.activity_byte_util;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = ActivityByteUtilBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    public void initView() {

        mBinding.includeToolbarByte.tvToolbarTitle.setText(getString(R.string.tools_byte));
        setSupportActionBar(mBinding.includeToolbarByte.toolbar);
        mBinding.includeToolbarByte.toolbar.setNavigationIcon(getResources().getDrawable(com.ethan.hydrogen.R.drawable.ic_arrow_back_24dp));
        // getBitValue
        byte value = (byte) 234;
        logger.debug("234 convert binary" + ByteUtil.getInstance().byteToBit(value));
        logger.debug("获取value index 为4的值" + ByteUtil.getInstance().getBitValue(value, 4));

        //byteArrayToShort 如果你不了解大小端 请参考 https://blog.wonium.com/archives/115/
        byte[] bytes = new byte[2];
        bytes[0] = 8;
        bytes[1] = 1;
        logger.debug("bytesArrayToShort Big-->" + ByteUtil.getInstance().byteArrayToShort(bytes, ByteOrder.BIG_ENDIAN));
        logger.debug("byteArrayToShort--Little>" + ByteUtil.getInstance().byteArrayToShort(bytes, ByteOrder.LITTLE_ENDIAN));

        //printByteArrayToBinary 如果你不了解大小端 请参考 https://blog.wonium.com/archives/115/
        logger.debug("shortToByteArray Big-Endian-->" + ByteUtil.getInstance().printByteArrayToBinary(ByteUtil.getInstance().shortToByteArray((short) 2049, ByteOrder.BIG_ENDIAN)));
        logger.debug("shortToByteArray Little-Endian-->" + ByteUtil.getInstance().printByteArrayToBinary(ByteUtil.getInstance().shortToByteArray((short) 2049, ByteOrder.LITTLE_ENDIAN)));


        // byteArrayToInt
        byte[] byteInt = new byte[4];
        byteInt[0] = 5;
        byteInt[1] = 1;
        byteInt[2] = 1;
        byteInt[3] = 64;
        logger.debug("byteArrayToInt Big-Endian-->" + ByteUtil.getInstance().byteArrayToInt(byteInt, ByteOrder.BIG_ENDIAN));
        logger.debug("byteArrayToInt Little-Endian-->" + ByteUtil.getInstance().byteArrayToInt(byteInt, ByteOrder.LITTLE_ENDIAN));

        // intToByteArray 83951936
        logger.debug("intToByteArray Big-Endian-->" + ByteUtil.getInstance().printByteArrayToBinary(ByteUtil.getInstance().intToByteArray(83951936, ByteOrder.BIG_ENDIAN)));
        logger.debug("intToByteArray Little-Endian-->" + ByteUtil.getInstance().printByteArrayToBinary(ByteUtil.getInstance().intToByteArray(83951936, ByteOrder.LITTLE_ENDIAN)));

        // byteArrayToFloat
        byte[] byteFloat = new byte[4];
        byteFloat[0] = 1;
        byteFloat[1] = 0;
        byteFloat[2] = 0;
        byteFloat[3] = 0;
        logger.debug("byteArrayToFloat Big-Endian-->" + ByteUtil.getInstance().byteArrayToFloat(byteFloat, ByteOrder.BIG_ENDIAN));
        logger.debug("byteArrayToFloat Little-Endian-->" + ByteUtil.getInstance().byteArrayToFloat(byteFloat, ByteOrder.LITTLE_ENDIAN));
    }

    @Override
    public void initListener() {
        mBinding.includeToolbarByte.toolbar.setNavigationOnClickListener(v -> finish());
    }
}
