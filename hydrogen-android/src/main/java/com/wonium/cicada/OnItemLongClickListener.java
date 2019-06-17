package com.wonium.cicada;

import android.view.View;

import androidx.annotation.Keep;

/**
 *  RecyclerView Item Long Click Listener
 */
@Keep
public interface OnItemLongClickListener {
    /**
     * RecyclerView Item Long Click
     * @param view
     * @param position
     */
    void onItemLongClick(View view,int position);
}
