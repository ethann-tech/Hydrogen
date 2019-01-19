package com.wonium.cicada.android.adapter;

import android.view.View;

public  abstract class BaseListViewHolder {
    private final View itemView;

  public BaseListViewHolder(View itemView) {
        this.itemView = itemView;
        itemView.setTag(this);
    }

    public View getItemView() {
        return itemView;
    }
}
