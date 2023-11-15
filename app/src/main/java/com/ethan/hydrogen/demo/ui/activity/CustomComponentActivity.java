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

    package com.ethan.hydrogen.demo.ui.activity;


    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.ethan.hydrogen.UniversalItemDecoration;
    import com.ethan.hydrogen.demo.databinding.ActivityCustomComponentBinding;
    import com.ethan.hydrogen.demo.R;
    import com.ethan.hydrogen.demo.adapter.CustomComponentAdapter;
    import com.ethan.hydrogen.demo.router.PageRouter;
    import com.ethan.hydrogen.demo.base.BaseActivity;

    import java.util.Arrays;

    import zlc.season.butterfly.Butterfly;
    import zlc.season.butterfly.annotation.Agile;

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
    @Agile(scheme = PageRouter.ACTIVITY_CUSTOM_COMPONENT)
    public class CustomComponentActivity extends BaseActivity {
        private ActivityCustomComponentBinding mBinding;
        private CustomComponentAdapter mAdapter;

        @Override
        protected int getStatusColor() {
            return getResources().getColor(com.ethan.hydrogen.R.color.black);
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
            mBinding = ActivityCustomComponentBinding.inflate(getLayoutInflater());
            setContentView(mBinding.getRoot());
        }

        @Override
        public void initView() {
            setStatusBar(true);
            mBinding.includeComponentToolbar.tvToolbarTitle.setText(getResources().getString(R.string.activity_custom_component));
            setSupportActionBar(mBinding.includeComponentToolbar.toolbar);
            mBinding.includeComponentToolbar.toolbar.setNavigationIcon(com.ethan.hydrogen.R.drawable.ic_arrow_back_24dp);
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
                    decoration.decorationColor = getResources().getColor(com.ethan.hydrogen.R.color.black_alpha_50);
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
                        Butterfly.INSTANCE.agile(PageRouter.ACTIVITY_IMAGE_VIEW) ;
                        break;
                    case 1:
                        Butterfly.INSTANCE.agile(PageRouter.ACTIVITY_TEXT_VIEW) ;
                        break;
                    case 2:
                        Butterfly.INSTANCE.agile(PageRouter.ACTIVITY_CUSTOM_DIALOG) ;
                        break;
                    case 3:
                        Butterfly.INSTANCE.agile(PageRouter.ACTIVITY_EXPAND_LIST) ;
                        break;
                    case 4:
                        Butterfly.INSTANCE.agile(PageRouter.ACTIVITY_RIPPLE_LAYOUT) ;
                        break;
                    case 5:
                        Butterfly.INSTANCE.agile(PageRouter.ACTIVITY_W_PROGRESS_DIALOG) ;
                        break;
                    case 6:
                        Butterfly.INSTANCE.agile(PageRouter.ACTIVITY_CROSS_VIEW) ;
                    default:
                        break;
                }
            });
        }
    }
