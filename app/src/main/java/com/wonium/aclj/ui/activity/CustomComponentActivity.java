    package com.wonium.aclj.ui.activity;

    import android.view.View;

    import androidx.databinding.DataBindingUtil;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.alibaba.android.arouter.facade.annotation.Route;
    import com.alibaba.android.arouter.launcher.ARouter;
    import com.wonium.aclj.R;
    import com.wonium.aclj.adapter.CustomComponentAdapter;
    import com.wonium.aclj.databinding.ActivityCustomComponentBinding;
    import com.wonium.aclj.router.PageRouter;
    import com.wonium.cicada.OnNoDoubleClickListener;
    import com.wonium.cicada.UniversalItemDecoration;
    import com.wonium.cicada.adapter.BaseRecyclerViewAdapter;
    import com.wonium.cicada.ui.BaseActivity;
    import com.wonium.cicada.ui.widget.LoadingDialog;
    import com.wonium.cicada.utils.StringUtil;

    import java.util.Arrays;
    import java.util.List;

    /**
     * @ClassName: CustomComponentActivity
     * @Description:
     * @author: ethan
     * @E-mail:
     * @Blog:
     * @CreateDate: 2019/6/3 16:23
     * @UpdateUser: dahai
     * @UpdateDate: 2019/6/3 16:23
     * @UpdateDescription:
     * @Version:
     */
    @Route(path = PageRouter.ACTIVITY_CUSTOM_COMPONENT)
    public class CustomComponentActivity extends BaseActivity {
        private ActivityCustomComponentBinding mBinding;
        private CustomComponentAdapter mAdapter;

        @Override
        protected int getStatusColor() {
            return getResources().getColor(R.color.black);
        }

        @Override
        public void initWindowAttributes() {
            setAllowFullScreen(false);
        }

        @Override
        public int getLayoutResId() {
            return R.layout.activity_custom_component;
        }

        @Override
        public void bindLayout(int layoutResId) {
            mBinding = DataBindingUtil.setContentView(this, layoutResId);
        }

        @Override
        public void initView() {
            setStatusBar(true);
            mBinding.setTitle(getResources().getString(R.string.activity_custom_component));
            setSupportActionBar(mBinding.includeComponentToolbar.toolbar);
            mBinding.includeComponentToolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
            mBinding.includeComponentToolbar.toolbar.setNavigationOnClickListener(v -> finish());

            String[] componentNameArrays = getResources().getStringArray(R.array.arrays_custom_component);
            mAdapter = new CustomComponentAdapter(getContext());
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(RecyclerView.VERTICAL);
            mBinding.includeRecycler.viewRecycler.setLayoutManager(manager);
            mBinding.includeRecycler.viewRecycler.addItemDecoration(new UniversalItemDecoration() {
                @Override
                public Decoration getItemOffsets(int position) {
                    ColorDecoration decoration = new ColorDecoration();
                    decoration.bottom = 2;
                    decoration.decorationColor = getResources().getColor(R.color.black_alpha_50);
                    return decoration;
                }
            });
            mBinding.includeRecycler.viewRecycler.setAdapter(mAdapter);
            mAdapter.setDatas(Arrays.asList(componentNameArrays));
        }

        @Override
        public void initListener() {
            mAdapter.setOnItemClickListener((view, position) -> {
                switch (position) {
                    case 0:
                        ARouter.getInstance().build(PageRouter.ACTIVITY_IMAGE_VIEW).navigation(getContext());
                        break;
                    case 1:
                        ARouter.getInstance().build(PageRouter.ACTIVITY_TEXT_VIEW).navigation(getContext());
                        break;
                    case 2:
                        ARouter.getInstance().build(PageRouter.ACTIVITY_CUSTOM_DIALOG).navigation(getContext());
                        break;
                    case 3:
                        ARouter.getInstance().build(PageRouter.ACTIVITY_EXPAND_LIST).navigation(getContext());
                        break;
                    case 4:
                        ARouter.getInstance().build(PageRouter.ACTIVITY_RIPPLE_LAYOUT).navigation(getContext());
                        break;
                    case 5: ARouter.getInstance().build(PageRouter.ACTIVITY_W_PROGRESS_DIALOG).navigation(getContext());
                        default: break;
                }
            });
        }
    }