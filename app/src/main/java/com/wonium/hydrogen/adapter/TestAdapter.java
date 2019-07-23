package com.wonium.hydrogen.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wonium.aclj.R;

import java.util.List;

public class TestAdapter extends BaseAdapter {
    private final String TAG =TestAdapter.class.getSimpleName();
    private List<String> datas;
    public TestAdapter(List<String> datas){
        this.datas =datas;
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;


        if (convertView != null && position == 0) {
            return convertView;
        }

        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_grid_view,parent,false);
            holder=new ViewHolder();
            holder.textView =convertView.findViewById(R.id.tvContent);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(datas.get(position));
        if (getSelectItemPosition() ==position){

            holder.textView.setBackgroundColor(parent.getContext().getResources().getColor(R.color.orchid));
        }else {
            holder.textView.setBackgroundColor(parent.getContext().getResources().getColor(R.color.colorPrimary));
        }

        Log.d(TAG, "getView: -->"+position);
        return convertView;
    }

    public static class ViewHolder {
        private TextView textView;
    }


    private int selectItem =-1;
    public void setSelectItemPosition(int position){
        this.selectItem =position;
        notifyDataSetChanged();
    }
    private int getSelectItemPosition(){
        return selectItem;
    }


}
