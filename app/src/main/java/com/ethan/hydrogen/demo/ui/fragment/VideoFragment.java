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

package com.ethan.hydrogen.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethan.hydrogen.demo.R;
import com.ethan.hydrogen.demo.databinding.FragmentVideoBinding;
import com.ethan.hydrogen.ui.BaseFragment;
import com.ethan.hydrogen.utils.ToastUtil;

public class VideoFragment extends BaseFragment {
    private FragmentVideoBinding mBinding;
    private String args1;
    private String args2;

    public static VideoFragment newInstance(String args1, String args2) {
        Bundle args = new Bundle();
        VideoFragment fragment = new VideoFragment();
        fragment.args1 = args1;
        fragment.args1 = args2;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_video;
    }

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {
        mBinding = FragmentVideoBinding.inflate(inflater);
        return mBinding.getRoot();
    }

    @Override
    protected void initView(View view) {
        mBinding.editFixedText.setFixedText("ZH-");
        mBinding.btnTest.setOnClickListener(v -> ToastUtil.getInstance().show(getContext(), mBinding.editFixedText.getText().toString().trim()));
    }

    @Override
    protected void initListener() {
        super.initListener();
    }
}
