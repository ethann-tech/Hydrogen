package com.wonium.cicada;

import android.os.Bundle;

import com.wonium.cicada.utils.StatusBarUtil;
import com.wonium.java.library.R;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.INSTANCE.setColor(this,getResources().getColor(R.color.darkOrchid));
    }
}
