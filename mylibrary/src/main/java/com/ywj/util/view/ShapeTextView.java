package com.ywj.util.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.ywj.util.R;


/**
 * 带圆角和选择器的自定义文本按钮
 * 实现自定义圆角背景
 * 支持：
 * 1.四边圆角
 * 2.指定边圆角
 * 3.支持填充色以及边框色
 * 4.支持按下效果
 */
public class ShapeTextView extends android.support.v7.widget.AppCompatTextView {

    public ShapeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    private GradientDrawable gd;
    //填充色
    private int solidColor = 0;
    //边框色
    private int strokeColor = 0;
    //按下填充色
    private int solidTouchColor = 0;
    //按下边框色
    private int strokeTouchColor = 0;
    //边框宽度
    private int strokeWith = 0;
    private int shapeType = 0;
    //按下字体色
    private int textTouchColor = 0;
    //字体色
    private int textColor = 0;

    float dashGap = 0;
    float dashWidth = 0;

    public void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ShapeTextView, 0, 0);

        solidColor = ta.getInteger(R.styleable.ShapeTextView_solidColor, 0x00000000);
        strokeColor = ta.getInteger(R.styleable.ShapeTextView_strokeColor, 0x00000000);

        solidTouchColor = ta.getInteger(R.styleable.ShapeTextView_solidTouchColor, 0x00000000);
        strokeTouchColor = ta.getInteger(R.styleable.ShapeTextView_strokeTouchColor, 0x00000000);
        textTouchColor = ta.getInteger(R.styleable.ShapeTextView_textTouchColor, 0x00000000);
        textColor = getCurrentTextColor();
        strokeWith = ta.getInteger(R.styleable.ShapeTextView_strokeWith, 0);

        float radius = ta.getDimension(R.styleable.ShapeTextView_radius, 0);
        float topLeftRadius = ta.getDimension(R.styleable.ShapeTextView_topLeftRadius, 0);
        float topRightRadius = ta.getDimension(R.styleable.ShapeTextView_topRightRadius, 0);
        float bottomLeftRadius = ta.getDimension(R.styleable.ShapeTextView_bottomLeftRadius, 0);
        float bottomRightRadius = ta.getDimension(R.styleable.ShapeTextView_bottomRightRadius, 0);
        dashGap = ta.getDimension(R.styleable.ShapeTextView_dashGap, 0);
        dashWidth = ta.getDimension(R.styleable.ShapeTextView_dashWidth, 0);


        shapeType = ta.getInt(R.styleable.ShapeTextView_shapeType, GradientDrawable.RECTANGLE);

        gd = new GradientDrawable();

        gd.setShape(shapeType);
        //矩形
        if (shapeType == GradientDrawable.RECTANGLE) {
            gd.setShape(GradientDrawable.RECTANGLE);
            if (radius != 0) {
                gd.setCornerRadius(radius);
            } else {
                //分别表示 左上 右上 右下 左下
                gd.setCornerRadii(new float[]{topLeftRadius, topLeftRadius, topRightRadius, topRightRadius, bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius});
            }
        }


        drowBackgroud(false);
        ta.recycle();
    }

    public void setSelection(boolean selection) {
        drowBackgroud(selection);
    }


    /**
     * 设置按下颜色值
     */
    private void drowBackgroud(boolean isTouch) {
        if (isTouch) {
            if (solidTouchColor != 0) {
                gd.setColor(solidTouchColor);
            }

            if (strokeWith != 0 && strokeTouchColor != 0) {
                if (dashGap != 0 && dashGap != 0) {
                    gd.setStroke(strokeWith, strokeTouchColor, dashGap, dashGap);
                } else {
                    gd.setStroke(strokeWith, strokeTouchColor);
                }
            }
            if (textTouchColor != 0) {
                setTextColor(textTouchColor);
            }
        } else {
            if (solidColor != 0) {
                gd.setColor(solidColor);
            } else {
                gd.setColor(Color.TRANSPARENT);
            }

            if (strokeWith != 0 && strokeColor != 0) {

                if (dashGap != 0 && dashGap != 0) {
                    gd.setStroke(strokeWith, strokeColor, dashGap, dashGap);
                } else {
                    gd.setStroke(strokeWith, strokeColor);
                }
            } else {
                gd.setStroke(0, Color.TRANSPARENT);
            }

            if (textTouchColor != 0) {
                setTextColor(textColor);
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(gd);
        }
        postInvalidate();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            drowBackgroud(true);
        } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
            drowBackgroud(false);
        }
        return super.onTouchEvent(event);
    }

    /**
     * 填充颜色
     * @param solidColor
     */
    public void setSolidColor(int solidColor) {
        this.solidColor = solidColor;
    }

    /**
     * 边框颜色
     * @param strokeColor
     */
    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    /**
     * 按下填充颜色
     * @param solidTouchColor
     */
    public void setSolidTouchColor(int solidTouchColor) {
        this.solidTouchColor = solidTouchColor;
    }

    /**
     * 按下边框颜色
     * @param strokeTouchColor
     */
    public void setStrokeTouchColor(int strokeTouchColor) {
        this.strokeTouchColor = strokeTouchColor;
    }

    /**
     * 边框宽度
     * @param strokeWith
     */
    public void setStrokeWith(int strokeWith) {
        this.strokeWith = strokeWith;
    }

    /**
     * 形状类型
     * @param shapeType
     */
    public void setShapeType(int shapeType) {
        this.shapeType = shapeType;
    }

    /**
     * 按下颜色
     * @param textTouchColor
     */
    public void setTextTouchColor(int textTouchColor) {
        this.textTouchColor = textTouchColor;
    }

    /**
     * 虚线间隙
     * @param dashGap
     */
    public void setDashGap(float dashGap) {
        this.dashGap = dashGap;
    }

    /**
     * 虚线宽度
     * @param dashWidth
     */
    public void setDashWidth(float dashWidth) {
        this.dashWidth = dashWidth;
    }


    /**
     * 刷新
     */
    public void refresh() {
        drowBackgroud(false);
    }
}
