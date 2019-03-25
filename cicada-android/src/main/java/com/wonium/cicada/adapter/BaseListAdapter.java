package com.wonium.cicada.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

public abstract class BaseListAdapter<T, VH extends BaseListAdapter.ZoeViewHolder> extends android.widget.BaseAdapter {

    private final String TAG = this.getClass().getSimpleName();

    public abstract VH createView(int position, ViewGroup parent);

    public abstract void bindView(int position, VH holder, T data);

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH holder;
        if (convertView == null) {
            holder = createView(position, parent);
            convertView = holder.getItemView();
            convertView.setTag(holder);
        } else {
            holder = (VH) convertView.getTag();
        }

        bindView(position, holder, datas.get(position));

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
        if (datas!=null){
            this.datas.clear();
        }
        notifyDataSetChanged();
    }

    public void setDatas(List<T> ts) {
        datas = ts;
        notifyDataSetChanged();
    }
    public List<T> getDatas(){
        if (datas==null){
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


    public  abstract class ZoeViewHolder {
        private final View itemView;

        public ZoeViewHolder(View itemView) {
            this.itemView = itemView;
            itemView.setTag(this);
        }

        public View getItemView() {
            return itemView;
        }
    }

}
