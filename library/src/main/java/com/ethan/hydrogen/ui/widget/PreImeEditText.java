package com.ethan.hydrogen.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * Created by DC on 2016/6/6.
 * 默认情况下，在SoftInput显示的时候，Back键会优先交给输入法处理，这时Back只会关闭输入法。需要再按Back,才能退出输入框所在的Activity,
 * 自定义EditText，在输入法响应Back事件之前，处理Back事件
 */
public class PreImeEditText extends AppCompatEditText {

    public PreImeListener mPreImeListener;

    public interface PreImeListener {
        void callBack();
    }

    public void setPreImeListener(PreImeListener listener){
        mPreImeListener = listener;
    }

    public PreImeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PreImeEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (mPreImeListener != null) {
                mPreImeListener.callBack();
            }
        }
        return super.onKeyPreIme(keyCode, event);
    }
}
