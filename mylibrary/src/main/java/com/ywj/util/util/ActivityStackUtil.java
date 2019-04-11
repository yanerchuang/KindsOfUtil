package com.ywj.util.util;

import android.app.Activity;

import java.util.Stack;

public class ActivityStackUtil {
    private static Stack<Activity> activityStack = new Stack<>();
    private static ActivityStackUtil instance;
    private static final int[] LOCK = new int[0];

    private ActivityStackUtil() {
    }

    public static ActivityStackUtil getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new ActivityStackUtil();
                }
            }
        }
        return instance;
    }


    /**
     * 入栈
     */
    public void pushActivity(Activity activity) {
        activityStack.add(activity);
//        LogUtil.e("push activityStack name:"+activity.getLocalClassName());
//        LogUtil.e("push activityStack size:"+activityStack.size());
    }

    /**
     * 出栈
     */
    public void popActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            activityStack.remove(activity);
        }
//        LogUtil.e("remove activityStack name:"+activity.getLocalClassName());
//        LogUtil.e("remove activityStack size:"+activityStack.size());
    }

    /**
     * 清空栈
     */
    public Activity getCurrentActivity() {
        Activity activity = null;
        if (!activityStack.isEmpty()) {
            activity = activityStack.lastElement();
        }
        return activity;
    }

    /**
     * 清空栈
     */
    public void closeAllActivity() {
        while (!activityStack.isEmpty()) {
            Activity activity = activityStack.pop();
            if (activity != null) {
                activity.finish();
            }
        }
    }
}
