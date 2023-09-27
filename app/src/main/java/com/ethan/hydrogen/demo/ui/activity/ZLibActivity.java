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

import android.Manifest;
import android.os.Build;
import android.os.Environment;

import com.ethan.hydrogen.demo.databinding.ActivityZlibBinding;
import com.ethan.hydrogen.demo.R;
import com.ethan.hydrogen.demo.base.BaseActivity;
import com.ethan.hydrogen.utils.ByteUtil;
import com.ethan.hydrogen.utils.FileUtil;
import com.ethan.hydrogen.utils.ToastUtil;
import com.ethan.hydrogen.utils.ZlibUtil;


import java.io.IOException;

import androidx.annotation.NonNull;


import ru.alexbykov.nopermission.PermissionHelper;

public class ZLibActivity extends BaseActivity {
    private ActivityZlibBinding mBinding;
    private byte[] src;
    private byte[] compressSrc;
    private PermissionHelper helper;

    @Override
    protected int getStatusColor() {
        return getResources().getColor(com.ethan.hydrogen.R.color.black);
    }

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_zlib;
    }

    @Override
    public void bindLayout(int layoutResId) {

        mBinding = ActivityZlibBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

    }

    @Override
    public void initView() {
        helper = new PermissionHelper(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            helper.check(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).onSuccess(this::onSuccess).onDenied(this::onDenied).onNeverAskAgain(this::onNeverAskAgain).run();
        }
    }

    /**
     * 初始化监听器
     */
    @Override
    public void initListener() {
        mBinding.btnCompress.setOnClickListener(view -> {
            src = mBinding.tvSrc.getText().toString().getBytes();
            compressSrc = ZlibUtil.getInstance().compress(src);

            new Thread(() -> {
                String pathPre = Environment.getExternalStorageDirectory() + FileUtil.getInstance().generateFilePath("test", "compressPre", ".zhd");
                String pathPreHex = Environment.getExternalStorageDirectory() + FileUtil.getInstance().generateFilePath("test", "compressPre-hex", ".zhd");
                String path = Environment.getExternalStorageDirectory() + FileUtil.getInstance().generateFilePath("test", "compressResult", ".zhd");
                FileUtil.getInstance().writeFile(pathPre, src);
                try {
                    FileUtil.getInstance().writeFile(pathPreHex, ByteUtil.getInstance().bytesToHex(src), false);
                    FileUtil.getInstance().writeFile(path, ByteUtil.getInstance().bytesToHex(compressSrc), false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            System.out.println(ByteUtil.getInstance().bytesToHex(compressSrc));
            mBinding.tvCompress.setText(new String(compressSrc));
        });
        mBinding.btnUnCompress.setOnClickListener(view -> mBinding.tvResult.setText(new String(ZlibUtil.getInstance().decompress(compressSrc))));
    }

    public void onSuccess() {
        ToastUtil.getInstance().show(getContext(), "授权成功");
    }

    public void onDenied() {
        ToastUtil.getInstance().show(getContext(), "权限拒绝");
    }

    public void onNeverAskAgain() {
        ToastUtil.getInstance().show(getContext(), "权限拒绝，并且不再询问");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        helper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
