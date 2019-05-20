package com.util.mylibs;


import android.os.Bundle;

import com.ywj.mylibs.R;
import com.ywj.util.base.BaseActivity;
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
    }

    @Override
    protected void loadData() {

    }

}
