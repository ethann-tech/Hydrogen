package com.wonium.cicada.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wonium.cicada.utils.ToastUtil;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @ClassName: BaseFragment
 * @Description: 描述一下
 * @Author: Ethan
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2019/3/27 21:29
 * @UpdateUser: update user
 * @UpdateDate: 2019/3/27 21:29
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
@Keep
public abstract class BaseFragment extends Fragment {
    private final String TAG=BaseFragment.class.getSimpleName();
    /**
     * 获取布局资源Id
     * @return 布局资源Id
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化DataBinding
     * @param inflater 加载布局资源得布局填充器
     * @param container 父rong'yi
     * @return
     */
    protected  abstract  View initBinding(LayoutInflater inflater,ViewGroup container);

    /**
     * 初始化View
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 初始化视图得点击事件
     */
    protected void initListener(){}
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        return initBinding(inflater,container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        initListener();
    }


    protected  void showToast(CharSequence content){
        ToastUtil.getInstance().show(getContext(),content);
    }
    protected void showToast(CharSequence content,int duration){
        ToastUtil.getInstance().showDuration(getContext(),content,duration);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
