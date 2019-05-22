package com.wonium.aclj.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ItemMyRecyclerViewBinding;
import com.wonium.cicada.adapter.BaseRecyclerViewAdapter;
import com.wonium.cicada.adapter.BaseViewHolder;

public class MyAdapter extends BaseRecyclerViewAdapter<String, MyAdapter.MyViewHolder> {

    public MyAdapter(Context context) {
        super(context);
    }

    @Override
    public MyViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ItemMyRecyclerViewBinding mBinding= DataBindingUtil.inflate(getInflater(), R.layout.item_my_recycler_view,parent,false);
        return new MyViewHolder(mBinding);
    }

    @Override
    public void onBindVH(MyViewHolder holder, int position) {
        holder.getBinding().tvItemData.setText(getData(position));
        holder.itemView.setOnClickListener(view -> onItemClickListener.onItemClick(view,position));
    }

    public class MyViewHolder extends BaseViewHolder<ItemMyRecyclerViewBinding>{

        public MyViewHolder(ItemMyRecyclerViewBinding binding) {
            super(binding);
        }
    }

}
