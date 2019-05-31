package com.util.mylibs;

import android.content.Context;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;
import com.ywj.util.util.LoggerUtil;

import java.util.Map;

/**
 * @author: ywj
 * @date: 2019/5/31 8:38
 */
public class MyReveiver extends MessageReceiver {

    @Override
    protected void onNotification(Context context, String s, String s1, Map<String, String> map) {
        super.onNotification(context, s, s1, map);
        LoggerUtil.e("MyReveiver-"+s+"--"+s1+"--"+map.toString());
    }

    @Override
    protected void onMessage(Context context, CPushMessage cPushMessage) {
        super.onMessage(context, cPushMessage);
        LoggerUtil.e("MyReveiver-"+cPushMessage.getTitle()+"--"+cPushMessage.getContent());
    }
}
