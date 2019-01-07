package com.wonium.aclj.ui.ui.frament;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.wonium.aclj.BR;
import com.wonium.aclj.R;
import com.wonium.aclj.databinding.FragmentFriendBinding;
import com.wonium.java.library.ui.fragment.BaseFragment;

/**
 * @ClassName: FriendFragment
 * @Description: 描述一下
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/12/2 17:42
 * @UpdateUser: update user
 * @UpdateDate: 2018/12/2 17:42
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
public class FriendFragment extends BaseFragment {
    private FragmentFriendBinding mBinding;
    private String argument1;
    private String argument2;

    public static FriendFragment newInstance(String argument1, String argument2) {
        Bundle args = new Bundle();
        FriendFragment fragment = new FriendFragment();
        args.putString("argument1", argument1);
        args.putString("argument2", argument2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void lazyload() {}


    @Override
    public void initArguments(String argument1, String argument2) {
        super.initArguments(argument1, argument2);
        this.argument1 = argument1;
        this.argument2 = argument2;
    }

    @Override
    public void initView(View view) {
        mBinding.setVariable(BR.btnFriend, argument1);
    }

    @Override
    public int getLayoutRedId() {
        return R.layout.fragment_friend;
    }

    @Override
    public void bindLayout(View view) {
        mBinding = DataBindingUtil.bind(view);
    }

    @Override
    public void initListener() {

    }
}
