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

package com.ethan.hydrogen.demo.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.ethan.hydrogen.demo.databinding.ItemSimpleDataViewBinding;
import com.ethan.hydrogen.demo.base.BaseRecyclerViewAdapter;
import com.ethan.hydrogen.demo.base.BaseViewHolder;

import java.util.Locale;

public class MyAdapter extends BaseRecyclerViewAdapter<String, BaseViewHolder<ItemSimpleDataViewBinding>> {

    public MyAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder<ItemSimpleDataViewBinding> onCreateVH(ViewGroup parent, int viewType) {
        ItemSimpleDataViewBinding mBinding = ItemSimpleDataViewBinding.inflate(getInflater(),parent,false);
        return new BaseViewHolder<>(mBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder<ItemSimpleDataViewBinding> holder, int position) {
        holder.getBinding().tvItemData.setText(getData(position));
        holder.getBinding().tvNumber.setText(String.format(Locale.CHINA, "%d", position));
    }
}
