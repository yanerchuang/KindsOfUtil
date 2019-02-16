package com.ywj.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.ywj.R;


/**
 * author: ywj
 * created on: 2018/10/31 11:21
 * description: 加载弹窗
 */
public class LoadingDialog extends BaseDialog {

    private static LoadingDialog loadingDialog;

    public static void show(Context context) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            return;
        }
        loadingDialog = new LoadingDialog(context);
        loadingDialog.show();
    }

    public static void cancle() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }


    public LoadingDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int setContentView() {
        return R.layout.dialog_loading_view;
    }

    @Override
    protected void initView(View rootView) {
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }
}
