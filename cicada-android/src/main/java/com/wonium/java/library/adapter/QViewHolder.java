package com.wonium.java.library.adapter;

import android.view.View;

public  abstract class QViewHolder {
    private final View itemView;

    QViewHolder(View itemView) {
        this.itemView = itemView;
        itemView.setTag(this);
    }

    public View getItemView() {
        return itemView;
    }
}
