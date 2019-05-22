package com.wonium.aclj.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wonium.aclj.R;
import com.wonium.cicada.adapter.BaseListAdapter;

public class TestGridViewAdapter extends BaseListAdapter<String, TestGridViewAdapter.GridViewHolder> {
    private final String TAG =TestGridViewAdapter.class.getSimpleName();
    @Override
    public GridViewHolder createViewHolder(int position, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_grid_view,parent,false);
        return new GridViewHolder(view);
    }

    @Override
    public void bindViewHolder(int position, GridViewHolder holder, String data) {
        holder.tvContent.setText(data);
        if (getSelectPosition()==position){
            holder.tvContent.setBackgroundColor(holder.getItemView().getResources().getColor(R.color.cadetBlue));
        }else {
            holder.tvContent.setBackgroundColor(holder.getItemView().getResources().getColor(R.color.orchid));
        }
    }

    public class GridViewHolder extends BaseListAdapter.ZoeViewHolder{
        private TextView tvContent;
        public GridViewHolder(View itemView) {
            super(itemView);
            tvContent =itemView.findViewById(R.id.tvContent);
        }
    }

}
