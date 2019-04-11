package com.ywj.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;


/**
 * author: ywj
 * created on: 2018/10/31 9:12
 * description:
 */
public class MyApplication extends MultiDexApplication {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

//        initBugly();
    }

//    private void initBugly() {
//        CrashReport.initCrashReport(getApplicationContext(), AppConfig.BUGLY_APP_ID, AppConfig.DEBUG);
//        CrashReport.setIsDevelopmentDevice(mContext, AppConfig.DEBUG);
//    }

    public static Context getAppContext() {
        return mContext;
    }



}
