package com.wonium.hydrogen.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ItemMyRecyclerViewBinding;
import com.wonium.cicada.adapter.BaseRecyclerViewAdapter;
import com.wonium.cicada.adapter.BaseViewHolder;

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
