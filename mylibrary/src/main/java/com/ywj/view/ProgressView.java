package com.ywj.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 圆角进度条
 */
public class ProgressView extends View {
    /*最大进度*/
    private float maxProgress = 100;
    /*当前进度*/
    private float currentProgress;
    /*圆角带大小*/
    private int round;

    private Paint mPaint;
    private int mWidth, mHeight;
    private RectF foregroundRectf, backgroundRectf;
    private int colorForeground = Color.BLUE, colorBackground = Color.GRAY;

    public ProgressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }


    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProgressView(Context context) {
        super(context);
        init(context);
    }

    /***
     * 设置最大的进度值
     * @param maxProgress
     */
    public void setMaxProgress(float maxProgress) {
        this.maxProgress = maxProgress;
    }

    /***
     * 设置当前的进度值，default max:100
     * @param currentCount
     */
    public void setCurrentProgress(float currentCount) {
        this.currentProgress = currentCount > maxProgress ? maxProgress : currentCount;
        invalidate();
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);


    }

    /*设置前景色*/
    public void setForegroundColor(int color) {
        colorForeground = color;
    }

    /*设置背景色*/
    @Override
    public void setBackgroundColor(int color) {
        colorBackground = color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        round = mHeight / 2;

        //绘制圆角矩形，背景色
        if (backgroundRectf == null) {
            backgroundRectf = new RectF(0, 0, mWidth, mHeight);
        }
        mPaint.setColor(colorBackground);
        canvas.drawRoundRect(backgroundRectf, round, round, mPaint);

        //设置进度条进度及颜色
        float percent = currentProgress / maxProgress;
        if (foregroundRectf == null) {
            foregroundRectf = new RectF(0, 0, mWidth * percent, mHeight);
        }
        foregroundRectf.right = mWidth * percent;
        if (percent == 0.0f) {
            mPaint.setColor(Color.TRANSPARENT);
        } else {
            mPaint.setColor(colorForeground);
        }
        canvas.drawRoundRect(foregroundRectf, round, round, mPaint);
    }

    private int dipToPx(int dip) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));//加0.5是为了四舍五入
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        //MeasureSpec.EXACTLY，精确尺寸
        if (widthSpecMode == MeasureSpec.EXACTLY || widthSpecMode == MeasureSpec.AT_MOST) {
            mWidth = widthSpecSize;
        } else {
            mWidth = 0;
        }
        //MeasureSpec.AT_MOST，最大尺寸，只要不超过父控件允许的最大尺寸即可，MeasureSpec.UNSPECIFIED未指定尺寸
        if (heightSpecMode == MeasureSpec.AT_MOST || heightSpecMode == MeasureSpec.UNSPECIFIED) {
            mHeight = dipToPx(20);
        } else {
            mHeight = heightSpecSize;
        }
        //设置控件实际大小
        setMeasuredDimension(mWidth, mHeight);
    }

}