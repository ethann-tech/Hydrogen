package com.wonium.cicada.android.ui.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * @ClassName: BaseFragment
 * @Description: 描述一下
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/12/2 15:04
 * @UpdateUser: update user
 * @UpdateDate: 2018/12/2 15:04
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
public abstract class BaseFragment extends Fragment {

    private String argument1;
    private String argument2;

    protected boolean isVisible = false;
    private boolean isFirstVisible = true;
    /**
     * 每页显示多少行
     */

    public final int rows = 10;
    public final String TAG = this.getClass().getSimpleName();


    protected abstract void lazyload();


    protected void onVisible() {
        lazyload();
    }

    protected void onInvisible() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            argument1 = getArguments().getString("argument1", "");
            argument2 = getArguments().getString("argument2", "");
            initArguments(argument1, argument2);
        }
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return DataBindingUtil.inflate(inflater, getLayoutRedId(), container, false).getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindLayout(view);

        initView(view);

        if (savedInstanceState != null) {
            initParam(savedInstanceState);
        }
        initListener();
    }


    /**
     * 初始化数据
     *
     * @param bundle 携带的参数
     */
    protected void initParam(Bundle bundle) {

    }



    /**
     * 初始化参数
     * @param argument1 参数1
     * @param argument2 参数2
     */
    public  void initArguments(String argument1, String argument2){}

    /**
     * 获取资源文件的Id
     *
     * @return
     */
    public abstract int getLayoutRedId();

    /**
     * 绑定布局
     *
     * @return
     */
    public abstract void bindLayout(View view);


    /**
     * 初始化View
     * @param view 布局View
     */
    public abstract void initView(View view);

    /**
     * 初始化监听器
     */
    public abstract void initListener();


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onPause() {
        super.onPause();

    }
}
