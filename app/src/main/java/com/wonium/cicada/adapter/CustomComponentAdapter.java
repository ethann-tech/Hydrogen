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

import com.wonium.cicada.databinding.ItemCustomComponentBinding;
import com.wonium.cicada.base.BaseRecyclerViewAdapter;
import com.wonium.cicada.base.BaseViewHolder;
import com.wonium.hydrogen.utils.StringUtil;
/**
 * @ClassName: CustomComponentAdapter
 * @Description: 自定义组件列表
 * @author: dahai
 * @E-mail:
 * @Blog:
 * @CreateDate: 2019/6/3 17:33
 * @UpdateUser: dahai
 * @UpdateDate: 2019/6/3 17:33
 * @UpdateDescription:
 * @Version:
 */
public class CustomComponentAdapter extends BaseRecyclerViewAdapter<String, BaseViewHolder<ItemCustomComponentBinding>> {
    public CustomComponentAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder<ItemCustomComponentBinding> onCreateVH(ViewGroup parent, int viewType) {
        ItemCustomComponentBinding mBinding = ItemCustomComponentBinding.inflate(getInflater());
        return new BaseViewHolder<>(mBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder<ItemCustomComponentBinding> holder, int position) {
            holder.getBinding().componentName.setText(StringUtil.getInstance().isEmpty(getData(position)));
    }
}
