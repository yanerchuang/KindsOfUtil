package com.ywj.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * author: ywj
 * created on: 2018/10/31 11:08
 * description:toast
 */
public class ToastUtil {
    private static Toast mToast;

    @SuppressLint("ShowToast")
    public static void show(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }

    public static void cancel() {
        if (mToast !=null){
            mToast.cancel();
        }
    }
}