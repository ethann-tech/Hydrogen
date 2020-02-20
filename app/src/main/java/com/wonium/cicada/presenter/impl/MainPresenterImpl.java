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

package com.wonium.cicada.presenter.impl;

import android.content.Context;


import com.wonium.cicada.model.BaseModel;
import com.wonium.cicada.model.MainModel;
import com.wonium.cicada.model.impl.MainModelImpl;
import com.wonium.cicada.presenter.MainPresenter;
import com.wonium.cicada.ui.view.MainView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @ClassName: MainPresenterImpl.java
 * @Description: 类描述
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/11/18 19:40
 * @UpdateUser: update user
 * @UpdateDate: 2018/11/18 19:40
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private MainModel mainModel;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        mainModel = new MainModelImpl();
    }

    @Override
    public void getListData(Context context) {
        if (mainView != null) {
            mainModel.getListData(context, new BaseModel.CallBack<List<String>, String>() {
                @Override
                public void onSuccess(List<String> result) {
                    mainView.updateListData(result);
                }

                @Override
                public void onFailure(String error) {
                    logger.debug("LOG:MainPresenterImpl:onFailure [error]={}",error);
                }
            });
        }

    }
}
