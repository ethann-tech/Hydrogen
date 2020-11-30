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

package com.wonium.cicada.base;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Keep;

@Keep
public abstract class BaseListAdapter<T, VH extends BaseListAdapter.ZoeViewHolder> extends android.widget.BaseAdapter {

    private final String TAG = this.getClass().getSimpleName();
    /**
     * 解决position ==0 多次调用时 view 复用，position再次为0时  数据不更新的问题
     */
    private boolean isViewRepeat=true;
    public abstract VH createViewHolder(int position, ViewGroup parent);

    public abstract void bindViewHolder(int position, VH holder, T data);

    private List<T> datas;
    private int selectPosition = -1;

    public BaseListAdapter() {
        this.datas = new ArrayList<>();
    }

    protected BaseListAdapter(List<T> datas) {
        this.datas = datas;
    }


    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    private int count =0;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH holder;

        if (convertView != null && position == 0&&isViewRepeat) {
            return convertView;
        }
        if (convertView == null) {
            holder = createViewHolder(position, parent);
            convertView = holder.getItemView();
            convertView.setTag(holder);
        } else {
            holder = (VH) convertView.getTag();
            isViewRepeat =false;
        }
        Log.d(TAG, "getView:position --->"+position);
        bindViewHolder(position, holder, datas.get(position));
        return convertView;
    }

    /**
     * 添加一条数据，并更新适配器
     *
     * @param data 数据
     */
    public void addData(T data) {
        this.datas.add(data);
        notifyDataSetChanged();
    }

    /**
     * 添加一个数据集合到原有数据集合里面，并更新适配器
     *
     * @param datas 新数据集合
     */
    public void addDatas(List<T> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 根据索引删除对应的数据，并更新适配器
     *
     * @param position 集合索引
     */
    public void removeData(int position) {
        this.datas.remove(position);
        notifyDataSetChanged();
    }

    /**
     * 删除指定的数据，并更新适配器
     *
     * @param data 被删除的数据
     */
    public void remoteData(T data) {
        this.datas.remove(data);
        notifyDataSetChanged();
    }

    /**
     * 数据集合清空，并更新适配器
     */
    public void clear() {
        if (datas != null) {
            this.datas.clear();
        }
        notifyDataSetChanged();
    }

    public void setDatas(List<T> ts) {
        datas = ts;
        notifyDataSetChanged();
    }

    public List<T> getDatas() {
        if (datas == null) {
            return new ArrayList<>();
        }
        return datas;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();
    }

    public int getSelectPosition() {
        return this.selectPosition;
    }


    protected OnChildItemClickListener onChildItemClickListener;

    public void setOnChildItemClickListener(OnChildItemClickListener onChildItemClickListener) {
        if (onChildItemClickListener == null) {
            Log.e(TAG, "setOnChildItemClickListener: " + "onChildItemClickListener is not null");
            return;
        }
        this.onChildItemClickListener = onChildItemClickListener;

    }

    public interface OnChildItemClickListener {
        void onClickChildItem(int position, View view);
    }


    public abstract class ZoeViewHolder {
        private final View itemView;
        public ZoeViewHolder(View itemView) {
            this.itemView = itemView;
        }

        public View getItemView() {
            return itemView;
        }
    }

}
