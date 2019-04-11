package com.util.mylibs;


import com.ywj.util.base.BaseActivity;
import com.ywj.util.util.LoggerUtil;
import com.ywj.mylibs.R;

public class MainActivity extends BaseActivity {
    @Override
    protected void setContentview() {
        setContentView(R.layout.activity_main);
//        LoggerUtil.init(this,"ccc",false,true);
        LoggerUtil.init(this, "ccc", true, true);
        LoggerUtil.e("dsasdasdasdadasdasdasd");
        LoggerUtil.e("dsasdasdasdadasdasdasd");
        LoggerUtil.d("dsasdasdasdadasdasdasd");
        LoggerUtil.d("dsasdasdasdadasdasdasd");
        LoggerUtil.wtf("dsasdasdasdadasdasdasd");
        LoggerUtil.wtf("dsasdasdasdadasdasdasd");
    }

    @Override
    protected void initView() {
        logE("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    }

    @Override
    protected void loadData() {

    }
}
