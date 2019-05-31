package com.util.mylibs;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.alibaba.sdk.android.push.register.HuaWeiRegister;
import com.ywj.util.BuildConfig;
import com.ywj.util.util.LoggerUtil;

public class MainApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LoggerUtil.init(this,"mylibs",BuildConfig.DEBUG,false);
        initCloudChannel(this);
    }

    /**
     * 初始化云推送通道
     *
     * @param applicationContext
     */
    private void initCloudChannel(Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.setDebug(BuildConfig.DEBUG);
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                LoggerUtil.e("MainApplication-" + "init cloudchannel success");
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                LoggerUtil.e("MainApplication-" + "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });


        HuaWeiRegister.register(applicationContext);
    }
}