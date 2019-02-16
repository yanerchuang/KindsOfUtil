package com.ywj.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * RecyclerView 垂直布局分割线
 */
public class DividerVerticalDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    private Drawable mDivider;
    private int dividerHeightPx;


    public DividerVerticalDecoration(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        dividerHeightPx = mDivider.getIntrinsicHeight();
    }

    public DividerVerticalDecoration(Context context, int dividerHeightPx) {
        this(context);
        this.dividerHeightPx = dividerHeightPx;
    }

    /**
     * 通过自定义资源分割线
     */
    public DividerVerticalDecoration(Drawable drawable, int dividerHeightPx) {
        mDivider = drawable;
        this.dividerHeightPx = dividerHeightPx;
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        drawVertical(c, parent);
    }


    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + dividerHeightPx;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }


    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (itemPosition == 0) {
            outRect.set(0, 0, 0, 0);
        } else {
            outRect.set(0, dividerHeightPx, 0, 0);
        }
    }
}