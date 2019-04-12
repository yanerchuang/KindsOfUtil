package com.util.mylibs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ywj.mylibs.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //    @Override
//    protected void setContentview() {
//
////        LoggerUtil.init(this,"ccc",false,true);
////        LoggerUtil.init(this, "ccc", true, true);
////        LoggerUtil.e("dsasdasdasdadasdasdasd");
////        LoggerUtil.e("dsasdasdasdadasdasdasd");
////        LoggerUtil.d("dsasdasdasdadasdasdasd");
////        LoggerUtil.d("dsasdasdasdadasdasdasd");
////        LoggerUtil.wtf("dsasdasdasdadasdasdasd");
////        LoggerUtil.wtf("dsasdasdasdadasdasdasd");
////        logE("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//    }
//
//    @Override
//    protected void initView() {
//
//    }
//
//    @Override
//    protected void loadData() {
//
//    }
}
