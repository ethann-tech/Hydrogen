package com.wonium.aclj.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ItemMyRecyclerViewBinding;
import com.wonium.cicada.adapter.BaseRecyclerViewAdapter;
import com.wonium.cicada.adapter.BaseViewHolder;

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
    }
}
