package com.wonium.cicada.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Keep;
import androidx.appcompat.widget.AppCompatEditText;


/**
 * 左边有固定文字EditText
 */
@Keep
public class FixedEditText extends AppCompatEditText {
    private String fixedText;
    private View.OnClickListener mListener;
    private int leftPadding;

    public FixedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setFixedText(String text) {
        fixedText = text;
        leftPadding = getPaddingLeft();

        int left = (int) getPaint().measureText(fixedText) + leftPadding;
        setPadding(left, getPaddingTop(), getPaddingBottom(), getPaddingRight());
        invalidate();
    }

    public String getFixedText() {
        return fixedText;
    }

    public void setDrawableClick(View.OnClickListener listener) {
        mListener = listener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!TextUtils.isEmpty(fixedText)) {
            canvas.drawText(fixedText, leftPadding, getBaseline(), getPaint());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mListener != null && getCompoundDrawables()[2] != null) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    int i = getMeasuredWidth() - getCompoundDrawables()[2].getIntrinsicWidth();
                    if (event.getX() > i) {
                        mListener.onClick(this);
                        return true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                default:
                    break;
            }
        }
        return super.onTouchEvent(event);
    }


}
