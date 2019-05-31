package com.ywj.util.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.ywj.util.R;


/**
 * author: ywj
 * created on: 2018/10/31 11:21
 * description: 加载弹窗
 */
public class LoadingDialog extends BaseDialog {
    private static int loadingLayoutId = R.layout.dialog_loading_view;

    private static OnLoadingListener loadingListener;

    /**
     * 可以实现自定义加载布局
     * @param loadingLayoutId
     */
    public static void setLoadingLayoutId(int loadingLayoutId) {
        LoadingDialog.loadingLayoutId = loadingLayoutId;
    }
    /**
     * 可以实现自定义加载监听
     * @param loadingListener
     */
    public static void setLoadingListener(OnLoadingListener loadingListener) {
        LoadingDialog.loadingListener = loadingListener;
    }

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
        return loadingLayoutId;
    }

    @Override
    protected void initView(View rootView) {
        this.rootView = rootView;
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        if (loadingListener != null) {
            loadingListener.onLoading(rootView);
        }

    }

    public interface OnLoadingListener {
        void onLoading(View rootView);
    }
}
