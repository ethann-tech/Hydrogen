package com.wonium.hydrogen.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ItemGuidePagerBinding;
import com.wonium.cicada.adapter.BaseRecyclerViewAdapter;
import com.wonium.cicada.adapter.BaseViewHolder;

public class GuidePagerAdapter extends BaseRecyclerViewAdapter<Integer, BaseViewHolder<ItemGuidePagerBinding>> {

    public GuidePagerAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder<ItemGuidePagerBinding> onCreateVH(ViewGroup parent, int viewType) {
        ItemGuidePagerBinding mBinding = DataBindingUtil.inflate(getInflater(), R.layout.item_guide_pager,parent,false);
        return new BaseViewHolder<>(mBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder<ItemGuidePagerBinding> holder, int position) {
        holder.getBinding().itemGuide.setImageResource(getData(position));
        holder.getBinding().itemGuide.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

    }
}
