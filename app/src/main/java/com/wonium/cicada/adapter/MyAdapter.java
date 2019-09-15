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

package com.wonium.cicada.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.wonium.cicada.R;
import com.wonium.cicada.databinding.ItemMyRecyclerViewBinding;
import com.wonium.hydrogen.adapter.BaseRecyclerViewAdapter;
import com.wonium.hydrogen.adapter.BaseViewHolder;

import java.util.Locale;

public class MyAdapter extends BaseRecyclerViewAdapter<String, BaseViewHolder<ItemMyRecyclerViewBinding>> {

    public MyAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder<ItemMyRecyclerViewBinding> onCreateVH(ViewGroup parent, int viewType) {
        ItemMyRecyclerViewBinding mBinding= DataBindingUtil.inflate(getInflater(), R.layout.item_my_recycler_view,parent,false);
        return  new BaseViewHolder<>(mBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder<ItemMyRecyclerViewBinding> holder, int position) {
        holder.getBinding().tvItemData.setText(getData(position));
        holder.getBinding().tvNumber.setText(String.format(Locale.CHINA,"%d",position));
    }
}
