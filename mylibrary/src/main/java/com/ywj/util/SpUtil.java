package com.ywj.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * author: ywj
 * created on: 2018/10/31 10:34
 * description: SharedPreferences
 */
public class SpUtil {

    private static final String ERROR_TAG = "Don't use is befor init SpUtil";


    private static SharedPreferences mSp;

    private SpUtil() {
    }

    public static synchronized void init(Context context,String spName) {
        if (mSp == null) {
            synchronized (SpUtil.class) {
                if (mSp == null) {
                    mSp = context.getApplicationContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
                }
            }
        }
    }

    public static void put(String key, Object value) {
        SharedPreferences.Editor editor = mSp.edit();
        if (value instanceof String) {

            editor.putString(key, (String) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else {
            editor.putString(key, GsonUtil.getGson().toJson(value));
        }
        editor.apply();
    }

    public static String getString(String key) {
        check();
        return mSp.getString(key, "");
    }

    public static Boolean getBoolean(String key) {
        check();
        return mSp.getBoolean(key, false);
    }

    public static int getInt(String key) {
        check();
        return mSp.getInt(key, 0);
    }

    public static <T> T get(String key, Class<T> classOfT) {
        String str = SpUtil.getString(key);
        return "".equals(str) ? null : GsonUtil.getGson().fromJson(str, classOfT);
    }

    public static void remove(String key) {
        check();
        mSp.edit().remove(key).apply();
    }

    public static void clear() {
        check();
        mSp.edit().clear().apply();
    }

    private static void check() {
        if (mSp == null) {
            throw new IllegalStateException(ERROR_TAG);
        }
    }

}
