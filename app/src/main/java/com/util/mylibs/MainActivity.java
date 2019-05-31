package com.util.mylibs;


import android.os.Bundle;

import com.ywj.mylibs.R;
import com.ywj.util.base.BaseActivity;
import com.ywj.util.net.ErrorHandleSubscriber;
import com.ywj.util.net.RetrofitManager;
import com.ywj.util.net.TestApi;
import com.ywj.util.net.log.ArmsRequestInterceptor;
import com.ywj.util.util.LoggerUtil;
import com.ywj.util.util.RxUtils;
import com.ywj.util.view.ShapeTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tv_asd)
    ShapeTextView tvAsd;

    @Override
    protected void setContentview() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        tvAsd.setText("123");
        RetrofitManager.init("http://www.baidu.com");
//        RetrofitManager.setInterceptor(new ArmsRequestInterceptor());
    }

    @Override
    protected void loadData() {

        RxUtils.loading(RetrofitManager.getRetrofit().create(TestApi.class).getxxx("xx","xxx"))
                .subscribe(new ErrorHandleSubscriber<Object>() {
                    @Override
                    public void onNext(Object o) {
                        LoggerUtil.e(o.toString());
                    }
                });
    }

}
