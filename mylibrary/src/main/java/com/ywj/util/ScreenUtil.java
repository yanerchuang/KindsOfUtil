package com.ywj.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.ref.WeakReference;

/**
 * author: ywj
 * created on: 2018/10/31 16:14
 * description:
 */
public class ScreenUtil {

    /**
     * 获取屏幕高度
     */
    public static int getScreenHeight(Activity activity) {
        WeakReference<Activity> weakReference = new WeakReference<>(activity);
        WindowManager wm = (WindowManager) weakReference.get().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
//        Rect rect = new Rect();
//        final View decorView = getWindow().getDecorView();
//        decorView.getWindowVisibleDisplayFrame(rect);
//        //计算出可见屏幕的高度
//        int displayHight = rect.bottom - rect.top;
//        //获得屏幕整体的高度
//        int hight = decorView.getHeight();
    }
    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth(Activity activity) {
        WeakReference<Activity> weakReference = new WeakReference<>(activity);
        WindowManager wm = (WindowManager) weakReference.get().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取状态栏高度
     *
     * @param activity
     * @return
     */
    public static int getStatusBarHeight(Activity activity) {
        int height = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }
}
