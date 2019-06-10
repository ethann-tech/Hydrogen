package com.wonium.aclj.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ItemCustomComponentBinding;
import com.wonium.cicada.adapter.BaseRecyclerViewAdapter;
import com.wonium.cicada.adapter.BaseViewHolder;
import com.wonium.cicada.utils.StringUtil;
/**
 * @ClassName: CustomComponentAdapter
 * @Description: 自定义组件列表
 * @author: dahai
 * @E-mail:
 * @Blog:
 * @CreateDate: 2019/6/3 17:33
 * @UpdateUser: dahai
 * @UpdateDate: 2019/6/3 17:33
 * @UpdateDescription:
 * @Version:
 */
public class CustomComponentAdapter extends BaseRecyclerViewAdapter<String, BaseViewHolder<ItemCustomComponentBinding>> {
    public CustomComponentAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder<ItemCustomComponentBinding> onCreateVH(ViewGroup parent, int viewType) {
        ItemCustomComponentBinding mBinding = DataBindingUtil.inflate(getInflater(), R.layout.item_custom_component,parent,false);
        return new BaseViewHolder<>(mBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder<ItemCustomComponentBinding> holder, int position) {
            holder.getBinding().setComponentName(StringUtil.getInstance().isEmpty(getData(position)));
    }
}
