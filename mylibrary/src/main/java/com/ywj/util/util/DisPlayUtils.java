package com.ywj.util.util;

import android.content.Context;
import android.view.View;

/**
 * author: ywj
 * created on: 2018/10/31 15:20
 * description:
 */
public class DisPlayUtils {

    public static void setViewVisible(View view) {

        if (view != null && view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void setViewInVisible(View view) {
        if (view != null && view.getVisibility() != View.INVISIBLE) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    public static void setViewGone(View view) {
        if (view != null && view.getVisibility() != View.GONE) {
            view.setVisibility(View.GONE);
        }
    }


    public static void setViewVisible(View view, int visible) {
        if (view != null && view.getVisibility() != visible) {
            view.setVisibility(visible);
        }
    }


    public static int dp2px(Context context,float dpValue) {
        float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(Context context,float pxValue) {
        float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2sp(Context context,float pxValue) {
        float fontScale = context.getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static float sp2px(Context context,float sp) {
        float scale = context.getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

}
