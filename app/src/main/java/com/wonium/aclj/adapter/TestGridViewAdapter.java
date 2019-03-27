package com.wonium.aclj.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ItemTestGridViewBinding;
import com.wonium.cicada.adapter.BaseListAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public class TestGridViewAdapter extends BaseListAdapter<String, TestGridViewAdapter.GridViewHolder> {


    @Override
    public GridViewHolder createView(int position, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_grid_view,parent,false);

        return new GridViewHolder(view);
    }

    @Override
    public void bindView(int position, GridViewHolder holder, String data) {
        holder.mBinding.setVariable(R.id.tvContent,data);
    }

    public class GridViewHolder extends BaseListAdapter.ZoeViewHolder{
        private ViewDataBinding mBinding;
        public GridViewHolder(View itemView) {
            super(itemView);
            mBinding= DataBindingUtil.findBinding(itemView);
        }
    }

}
