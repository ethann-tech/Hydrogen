package com.ethan.hydrogen.demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.ethan.hydrogen.demo.base.BaseViewHolder
import com.ethan.hydrogen.demo.databinding.ItemSimpleDataViewBinding
import java.util.Locale

class AdapterSimpleData : BaseQuickAdapter<String, BaseViewHolder<ItemSimpleDataViewBinding>>() {

    override fun onBindViewHolder(holder: BaseViewHolder<ItemSimpleDataViewBinding>, position: Int, item: String?) {
        holder.binding.tvItemData.text = item
        holder.binding.tvNumber.text = String.format(Locale.getDefault(), "%d", position + 1)
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): BaseViewHolder<ItemSimpleDataViewBinding> {
        val binding = ItemSimpleDataViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return BaseViewHolder(binding)
    }
}