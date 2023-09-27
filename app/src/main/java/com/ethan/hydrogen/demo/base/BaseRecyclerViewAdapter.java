/*
 * Copyright (C) 2019 Ethan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ethan.hydrogen.demo.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ethan.hydrogen.OnItemLongClickListener;


/**
 * @ClassName: BaseRecyclerViewAdapter.java
 * @Description: Adapter的公共基类
 * @Author: Ethan
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/11/17 10:15
 * @UpdateUser: update user
 * @UpdateDate: 2018/11/17 10:15
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
@Keep
public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private List<T> datas;
    private LayoutInflater inflater;
    private Context context;
    private  OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    /**
     * 设置Item得点击事件
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 长按监听事件
     * @param onItemLongClickListener 长按监听事件
     */
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener){
        this.onItemLongClickListener=onItemLongClickListener;
    }


    /**
     * Item 点击接口
     */
    @Keep
    public interface OnItemClickListener {
        /**
         * Item 点击事件
         * @param view 被点击的View
         * @param position 在列表中的位置
         */
        void onItemClick(View view, int position);
    }

    public BaseRecyclerViewAdapter(Context context) {
        this.datas = new ArrayList<>();
        this.context =context;
        inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     *
     * @return
     */
    public Context getContext() {
        return context;
    }

    /**
     * 获取LayoutInflater对象
     * @return
     */
    public LayoutInflater getInflater() {
        return inflater;
    }

    /**
     * 设置LayoutInflater
     * @param inflater LayoutInflater对象
     */
    private void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onCreateVH(parent, viewType);
    }

    /**
     * 创建ViewHolder
     * @param parent
     * @param viewType View雷西那个
     * @return ViewHolder
     */
    public abstract VH onCreateVH(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(view, position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onItemLongClick(view, position);
                }
                return false;
            }
        });
        onBindVH(holder, position);
    }

    /**
     * 绑定ViewHolder
     * @param holder ViewHolder对象
     * @param position 位置
     */
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

    /**
     * 获取所有的数据
     * @return datas
     */
    public List<T> getDatas() {
        return datas;
    }


    /**
     * 添加一个泛型对象到集合中
     * @param data 泛型对象
     */
    public void addData(T data) {
        this.datas.add(data);
        notifyDataSetChanged();
    }
    public T getData(int position){
        return datas.get(position);
    }
    /**
     * 追加一个泛型集合到datas集合中
     * @param datas
     */
    public void addAll(List<T> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 设置数据，并通知刷新列表
     * @param datas
     */
    public void setDatas(List<T> datas){
        this.datas=datas;
        notifyDataSetChanged();
    }

}
