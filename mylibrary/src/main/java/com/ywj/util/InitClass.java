package com.ywj.util;

import android.content.Context;

import com.ywj.util.net.RetrofitManager;
import com.ywj.util.util.LoggerUtil;

import butterknife.ButterKnife;

/**
 * @author: ywj
 * @date: 2019/5/31 17:47
 */
public class InitClass {
    /**
     * classpath 'com.jakewharton:butterknife-gradle-plugin:9.0.0-SNAPSHOT'
     * apply plugin: 'com.jakewharton.butterknife'
     * annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'
     */


    public static boolean LOG_DEBUG = true;

    public static void init(Context context) {
        ButterKnife.setDebug(LOG_DEBUG);
        RetrofitManager.init("http://www.baidu.com");
        RetrofitManager.initResponseErrorListener(context, (context1, t) -> {
            //处理错误信息
        });
        LoggerUtil.init(context, "mylibs", BuildConfig.DEBUG, false);
    }
}
