package com.wonium.aclj.ui.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wonium.aclj.R;
import com.wonium.cicada.android.ui.activity.SwipeBackActivity;
import com.wonium.cicada.android.ui.weight.SwipeBackLayout;


/**
 * Created by DongBang on 2016/6/7.
 */
public class SecondActivity extends SwipeBackActivity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setDragEdge(SwipeBackLayout.DragEdge.NONE);
    }
}
