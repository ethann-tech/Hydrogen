package com.wonium.cicada.android.adapter;

import android.view.View;

public  abstract class QViewHolder {
    private final View itemView;

  public  QViewHolder(View itemView) {
        this.itemView = itemView;
        itemView.setTag(this);
    }

    public View getItemView() {
        return itemView;
    }
}
