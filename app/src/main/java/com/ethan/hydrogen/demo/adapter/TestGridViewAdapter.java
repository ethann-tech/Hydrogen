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

package com.ethan.hydrogen.demo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ethan.hydrogen.demo.R;
import com.ethan.hydrogen.demo.base.BaseListAdapter;

public class TestGridViewAdapter extends BaseListAdapter<String, TestGridViewAdapter.GridViewHolder> {
    private final String TAG =TestGridViewAdapter.class.getSimpleName();
    @Override
    public GridViewHolder createViewHolder(int position, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_grid_view,parent,false);
        return new GridViewHolder(view);
    }

    @Override
    public void bindViewHolder(int position, GridViewHolder holder, String data) {
        holder.tvContent.setText(data);
        if (getSelectPosition()==position){
            holder.tvContent.setBackgroundColor(holder.getItemView().getResources().getColor(com.ethan.hydrogen.R.color.cadetBlue));
        }else {
            holder.tvContent.setBackgroundColor(holder.getItemView().getResources().getColor(com.ethan.hydrogen.R.color.orchid));
        }
    }

    class GridViewHolder extends BaseListAdapter.ZoeViewHolder{
        private TextView tvContent;
        GridViewHolder(View itemView) {
            super(itemView);
            tvContent =itemView.findViewById(R.id.tvContent);
        }
    }

}
