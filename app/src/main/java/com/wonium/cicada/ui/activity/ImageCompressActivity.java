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

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.lcw.library.imagepicker.ImagePicker;
import com.wonium.cicada.GlideLoader;
import com.wonium.cicada.R;
import com.wonium.cicada.config.RequestCode;
import com.wonium.cicada.databinding.ActivityImageCompressBinding;
import com.wonium.cicada.router.PageRouter;
import com.wonium.hydrogen.ui.BaseActivity;
import com.wonium.hydrogen.utils.BitmapUtil;

import java.util.List;

@Route(path = PageRouter.ACTIVITY_IMG_COMPRESS)
public class ImageCompressActivity extends BaseActivity {
    private ActivityImageCompressBinding mBinding;
    private List<String> mImagePaths;

    @Override
    protected int getStatusColor() {
        return getResources().getColor(R.color.black);
    }

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
        setScreenRotate(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_image_compress;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = DataBindingUtil.setContentView(this, layoutResId);
    }

    @Override
    public void initView() {
        setSupportActionBar(mBinding.includeCompressToolbar.toolbar);
        mBinding.setTitle("图片压缩");
        mBinding.includeCompressToolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
    }

    @Override
    public void initListener() {
        mBinding.includeCompressToolbar.toolbar.setNavigationOnClickListener(v -> finish());
        mBinding.btnSizeCompress.setOnClickListener(v -> openImagePicker(RequestCode.REQUEST_SELECT_IMAGE_SIZE_COMPRESS));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_select:
                openImagePicker(RequestCode.REQUEST_SELECT_IMAGES_CODE);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openImagePicker(int requestCode) {
        ImagePicker.getInstance()
                .setTitle("图片")//设置标题
                .showCamera(true)//设置是否显示拍照按钮
                .showImage(true)//设置是否展示图片
                .showVideo(false)//设置是否展示视频
                .setMaxCount(9)//设置最大选择图片数目(默认为1，单选)
                .setSingleType(true)//设置图片视频不能同时选择
//                .setImagePaths(mImagePaths)//设置历史选择记录
                .setImageLoader(new GlideLoader())//设置自定义图片加载器
                .start(ImageCompressActivity.this, requestCode);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCode.REQUEST_SELECT_IMAGE_SIZE_COMPRESS && resultCode == RESULT_OK) {
            if (data != null) {
                mImagePaths = data.getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES);
                sizeCompress(mImagePaths.get(0));
            }
        }
    }


    private void sizeCompress(String path) {
//        Glide.with(getContext()).load(path).into(mBinding.imgDisplaySrcImage);
        Bitmap bitmap = BitmapUtil.getInstance().sizeCompress(path,128,64);
        Glide.with(getContext()).load(bitmap).placeholder(R.drawable.icon_image_error_1).error(R.drawable.icon_image_error).into(mBinding.imgDisplayCompressImage);
    }


}
