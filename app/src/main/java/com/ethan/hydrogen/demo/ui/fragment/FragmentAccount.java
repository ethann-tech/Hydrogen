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
import com.ethan.hydrogen.demo.databinding.FragmentAccountBinding;
import com.ethan.hydrogen.ui.BaseFragment;
import com.ethan.hydrogen.demo.router.PageRouter;

import zlc.season.butterfly.Butterfly;


public class FragmentAccount extends BaseFragment {
    private FragmentAccountBinding mBinding;
    private String args1;
    private String args2;


    public static FragmentAccount newInstance(String args1, String args2) {
        Bundle args = new Bundle();
        FragmentAccount fragment = new FragmentAccount();
        fragment.args1 = args1;
        fragment.args2 = args2;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_account;
    }

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {
        mBinding = FragmentAccountBinding.inflate(inflater);
        return mBinding.getRoot();
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initListener() {
        super.initListener();
        mBinding.btnFitter.setOnClickListener(v -> Butterfly.INSTANCE.agile(PageRouter.ACTIVITY_FITTER).carry(requireContext(), null, null, null));
    }
}
