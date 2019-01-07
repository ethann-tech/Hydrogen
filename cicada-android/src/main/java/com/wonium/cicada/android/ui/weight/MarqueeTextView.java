package com.wonium.cicada.android.ui.weight;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * @ClassName: MarqueeTextView
 * @Description: 具有跑马灯效果的TextView 原生坑多
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/12/7 16:49
 * @UpdateUser: 添加更新者
 * @UpdateDate:  2018/12/7 16:49
 * @UpdateDescription: 更新描述
 * @Version:
 */
public class MarqueeTextView extends AppCompatTextView {
    public MarqueeTextView(Context con) {
        super(con);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
