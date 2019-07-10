package com.wonium.aclj.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ItemColorUtilGradientatorBinding;
import com.wonium.cicada.adapter.BaseRecyclerViewAdapter;
import com.wonium.cicada.adapter.BaseViewHolder;

public class ColorGradientatorAdapter extends BaseRecyclerViewAdapter<Integer, BaseViewHolder<ItemColorUtilGradientatorBinding>> {
    public ColorGradientatorAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder<ItemColorUtilGradientatorBinding> onCreateVH(ViewGroup parent, int viewType) {
        ItemColorUtilGradientatorBinding mBinding = DataBindingUtil.inflate(getInflater(), R.layout.item_color_util_gradientator, parent, false);
        return new BaseViewHolder<>(mBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder<ItemColorUtilGradientatorBinding> holder, int position) {
        holder.getBinding().setColor(getData(position));
    }
}
