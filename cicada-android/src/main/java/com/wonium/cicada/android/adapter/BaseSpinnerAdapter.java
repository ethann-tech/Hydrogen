package com.wonium.cicada.android.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


public abstract class BaseSpinnerAdapter<T> extends BaseAdapter {
    private List<T> datas;
    private LayoutInflater inflater;

    public BaseSpinnerAdapter(List<T> datas) {
        this.datas = datas;
    }

    protected abstract ViewHolder onCreateViewHolder(int position, ViewGroup parent);

    protected abstract void onBindViewHolder(int position, ViewHolder holder);

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (inflater == null) {
            inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (contentView == null) {
            holder = onCreateViewHolder(position, viewGroup);
            contentView = holder.getBinding().getRoot();
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }
        onBindViewHolder(position, holder);
        return contentView;
    }

    @Override
    public View getDropDownView(int position, View contentView, ViewGroup parent) {
        ViewHolder holder;
        if (inflater == null) {
            inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (contentView == null) {
            holder = onCreateViewHolder(position, parent);
            contentView = holder.getBinding().getRoot();
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }
        onBindViewHolder(position, holder);
        return contentView;
    }

    public static final class ViewHolder {
        private ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

    public LayoutInflater getInflater() throws NullPointerException {
        return inflater;
    }

    public void addData(T data) {
        this.datas.add(data);
        notifyDataSetChanged();
    }

    public void addAllData(List<T> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public void removeDataByPosition(int position) {
        this.datas.remove(position);
        notifyDataSetChanged();
    }

    public void setDatas(List<T> datas){
        this.datas=datas;
        notifyDataSetChanged();
    }

    public List<T> getAllDatas() {
        return this.datas;
    }
}
