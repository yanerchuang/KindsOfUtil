package com.ywj.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ywj.R;


/**
 * author: ywj
 * created on: 2018/10/31 11:16
 * description:
 */
public abstract class BaseDialog extends Dialog {

    private float widthPercent = 1.0f;
    private int gravity = Gravity.CENTER;
    protected Context mContext;
    protected View rootView;


    public BaseDialog(@NonNull Context context) {
        super(context, R.style.dialog_common);
        mContext = context;
    }

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = LayoutInflater.from(getContext()).inflate(setContentView(), null);
        initView(rootView);
        setContentView(rootView);
        setDialogWidth();
    }

    private void setDialogWidth() {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.width = (int) (mContext.getApplicationContext().getResources().getDisplayMetrics().widthPixels * widthPercent);
        layoutParams.gravity = gravity;
        dialogWindow.setAttributes(layoutParams);
    }

    protected abstract int setContentView();

    protected abstract void initView(View rootView);


    public BaseDialog setDialogWidth(float widthPercent) {
        this.widthPercent = widthPercent;
        return this;
    }

    public BaseDialog setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }
}
