package com.wonium.cicada.android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 *
 * @author :  admin
 * @Project Name :  UPClientManage
 * @Package Name :  com.hgtech.upmanage.base
 * @Description :  通用数据适配器 不含DataBinding
 * @Creation Date:  2018-03-20 17:56
 * @ModificationHistory Who    When    What
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 */
public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private List<T> datas;
    private LayoutInflater inflater;
    private Context context;

    public BaseRecyclerAdapter(Context context) {
        this.datas = new ArrayList<>();
        this.context = context;
        inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Context getContext() {
        return context;
    }


    public LayoutInflater getInflater() {
        return inflater;
    }

    private void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return onCreateVH(parent, viewType);
    }

    public abstract VH onCreateVH(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        onBindVH(holder, position);
    }

    public abstract void onBindVH(VH holder, int position);


    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 加载更多
     *
     * @param data 加载的新数据
     */
    public void loadMoreData(List<T> data) {
        this.datas.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 刷新数据
     *
     * @param data 数据源
     */
    public void refreshData(List<T> data) {
        this.datas.clear();
        this.datas.addAll(data);
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return datas;
    }

    public void setData(List<T> data) {
        this.datas = data;
    }

    public void addData(T data) {
        this.datas.add(data);
        notifyDataSetChanged();
    }

    public void addAll(List<T> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public T getItem(int positon) {
        if (datas != null && datas.size() > 0) {
            return datas.get(positon);
        }
        return null;
    }
}
