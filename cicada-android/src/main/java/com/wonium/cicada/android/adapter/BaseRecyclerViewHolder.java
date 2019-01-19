package com.wonium.cicada.android.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

public class BaseRecyclerViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private B mBinding;

    public BaseRecyclerViewHolder(B binding) {
        super(binding.getRoot());
        this.mBinding =binding;
    }
    public B getBinding(){
        return  mBinding;
    }
}
