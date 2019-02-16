package com.ywj.util;

import android.text.TextUtils;
import android.util.Log;

import com.ywj.BuildConfig;

/**
 * author: ywj
 * created on: 2018/10/31 10:33
 * description:
 */
public class LogUtil {
    private static String customTagPrefix = LogUtil.class.getSimpleName();
    private static boolean isDebug = BuildConfig.DEBUG;

    public static void v(String string) {
        if (isDebug) {
            Log.v(generateTag(), string);
        }
    }

    public static void d(String string) {
        if (isDebug) {
            Log.d(generateTag(), string);
        }
    }

    public static void i(String string) {
        if (isDebug) {
            Log.i(generateTag(), string);
        }
    }

    public static void w(String string) {
        if (isDebug) {
            Log.w(generateTag(), string);
        }
    }

    public static void e(String string) {
        if (isDebug) {
            Log.e(generateTag(), string);
        }
    }

    private static String generateTag() {
        try {
            StackTraceElement caller = new Throwable().getStackTrace()[2];
            String tag = "%s.%s(L:%d)";
            String callerClazzName = caller.getClassName();
            callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
            tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
            tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
            return tag;
        } catch (Exception e) {
            e.printStackTrace();
            return customTagPrefix;
        }
    }
}
