package com.ywj.mylibs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ywj.adapter.MyAdapter;
import com.ywj.view.ShapeTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyAdapter s;
        ShapeTextView t;
    }
}
