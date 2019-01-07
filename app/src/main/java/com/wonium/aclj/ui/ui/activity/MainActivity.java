package com.wonium.aclj.ui.ui.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivityMainBinding;
import com.wonium.aclj.ui.ui.frament.AccountFragment;
import com.wonium.aclj.ui.ui.frament.FindFragment;
import com.wonium.aclj.ui.ui.frament.FriendFragment;
import com.wonium.aclj.ui.ui.frament.MyFragment;
import com.wonium.aclj.ui.ui.frament.VideoFragment;
import com.wonium.extension.utils.ToastUtil;
import com.wonium.cicada.android.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MainActivity
 * @Description: 添加描述
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/11/28 14:14
 * @UpdateUser: 添加更新者
 * @UpdateDate: 2018/11/28 14:14
 * @UpdateDescription: 更新描述
 * @Version:
 */
public class MainActivity extends BaseActivity {
    private ActivityMainBinding mBinding;
    private List<Fragment> fragments;

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = DataBindingUtil.setContentView(this, layoutResId);
    }

    @Override
    public void initView() {
        initFragment();
    }

    @Override
    public void initListener() {
        mBinding.includeMainContent.bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()) {
                case R.id.nav_find:
                    replaceFragment(lastShowFragment,0);
                    ToastUtil.INSTANCE.show(getContext(), "find");

                    break;
                case R.id.nav_video:
                    replaceFragment(lastShowFragment,1);
                    break;
                case R.id.nav_my:
                    replaceFragment(lastShowFragment,2);
                    ToastUtil.INSTANCE.show(getContext(), "my");
                    break;
                case R.id.nav_friend:
                    replaceFragment(lastShowFragment,3);
                    ToastUtil.INSTANCE.show(getContext(), "friend");
                    break;
                case R.id.nav_account:
                    replaceFragment(lastShowFragment,4);
                    ToastUtil.INSTANCE.show(getContext(), "account");
                    break;
                default:
                    ToastUtil.INSTANCE.show(getContext(), "find");
                    break;
            }
            return true;
        });
    }
    private int lastShowFragment=0;
    private void initFragment() {

        fragments = new ArrayList<>();
        fragments.add(FindFragment.newInstance("FindFragment", "0"));
        fragments.add(VideoFragment.newInstance("VideoFragment", "1"));
        fragments.add(MyFragment.newInstance("MyFragment","2"));
        fragments.add(FriendFragment.newInstance("FriendFragment","3"));
        fragments.add(AccountFragment.newInstance("AccountFragment","4"));

        getSupportFragmentManager()
                .beginTransaction()
                .add(mBinding.includeMainContent.container.getId(),fragments.get(0))
                .show(fragments.get(0))
                .commitAllowingStateLoss();
    }

    private  void replaceFragment(int lastShowFragment,int index){
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments.get(lastShowFragment));
        this.lastShowFragment =index;
        if (!fragments.get(index).isAdded()){
            transaction.add(mBinding.includeMainContent.container.getId(),fragments.get(index));
        }
        transaction.show(fragments.get(index)).commitAllowingStateLoss();
    }



}
