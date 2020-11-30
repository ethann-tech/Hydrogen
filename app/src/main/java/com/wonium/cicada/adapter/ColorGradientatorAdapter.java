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

import com.wonium.cicada.databinding.ItemColorUtilGradientatorBinding;
import com.wonium.cicada.base.BaseRecyclerViewAdapter;
import com.wonium.cicada.base.BaseViewHolder;

public class ColorGradientatorAdapter extends BaseRecyclerViewAdapter<Integer, BaseViewHolder<ItemColorUtilGradientatorBinding>> {
    public ColorGradientatorAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder<ItemColorUtilGradientatorBinding> onCreateVH(ViewGroup parent, int viewType) {
        ItemColorUtilGradientatorBinding mBinding = ItemColorUtilGradientatorBinding.inflate(getInflater());
        return new BaseViewHolder<>(mBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder<ItemColorUtilGradientatorBinding> holder, int position) {
        holder.getBinding().itemGradientator.setImageResource(getData(position));
    }
}
