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
import android.view.View;
import android.view.ViewGroup;

import com.wonium.cicada.databinding.ItemGuidePagerBinding;
import com.wonium.cicada.base.BaseRecyclerViewAdapter;
import com.wonium.cicada.base.BaseViewHolder;

public class GuidePagerAdapter extends BaseRecyclerViewAdapter<Integer, BaseViewHolder<ItemGuidePagerBinding>> {

    public GuidePagerAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder<ItemGuidePagerBinding> onCreateVH(ViewGroup parent, int viewType) {
        ItemGuidePagerBinding mBinding = ItemGuidePagerBinding.inflate(getInflater());
        return new BaseViewHolder<>(mBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder<ItemGuidePagerBinding> holder, int position) {
        holder.getBinding().itemGuide.setImageResource(getData(position));
        holder.getBinding().itemGuide.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }
}
