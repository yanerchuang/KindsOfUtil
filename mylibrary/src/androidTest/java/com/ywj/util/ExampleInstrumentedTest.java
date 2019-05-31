package com.ywj.util;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ywj.util.net.ErrorHandleSubscriber;
import com.ywj.util.net.RetrofitManager;
import com.ywj.util.net.TestApi;
import com.ywj.util.util.LoggerUtil;
import com.ywj.util.util.RxUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {

        RetrofitManager manager = RetrofitManager.getInstance();
        manager.init("http://www.baidu.com");
        RxUtils.loading(manager.getRetrofit().create(TestApi.class).getxxx("xx","xxx"))
                .subscribe(new ErrorHandleSubscriber<Object>() {
                    @Override
                    public void onNext(Object o) {
                       //
                    }
                });
    }
}
