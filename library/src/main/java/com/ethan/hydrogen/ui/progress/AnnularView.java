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

package com.ethan.hydrogen.ui.progress;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.ethan.hydrogen.R;
import com.ethan.hydrogen.utils.DensityUtil;

class AnnularView extends View implements Determinate {

    private Paint mWhitePaint;
    private Paint mGreyPaint;
    private RectF mBound;
    private int mMax = 100;
    private int mProgress = 0;

    public AnnularView(Context context) {
        super(context);
        init(context);
    }

    public AnnularView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AnnularView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        mWhitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWhitePaint.setStyle(Paint.Style.STROKE);
        mWhitePaint.setStrokeWidth(DensityUtil.getInstance().dip2px(getContext(),3));
        mWhitePaint.setColor(Color.WHITE);

        mGreyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGreyPaint.setStyle(Paint.Style.STROKE);
        mGreyPaint.setStrokeWidth(DensityUtil.getInstance().dip2px(getContext(),3));
        mGreyPaint.setColor(context.getResources().getColor(R.color.progress_grey_color));

        mBound = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int padding =DensityUtil.getInstance().dip2px(getContext(),4);
        mBound.set(padding, padding, w - padding, h - padding);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float mAngle = mProgress * 360f / mMax;
        canvas.drawArc(mBound, 270, mAngle, false, mWhitePaint);
        canvas.drawArc(mBound, 270 + mAngle, 360 - mAngle, false, mGreyPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int dimension = DensityUtil.getInstance().dip2px(getContext(),40);
        setMeasuredDimension(dimension, dimension);
    }

    @Override
    public void setMax(int max) {
        this.mMax = max;
    }

    @Override
    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }
}
