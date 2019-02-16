package com.ywj.dialog;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.View;

import com.ywj.R;
import com.ywj.view.ShapeTextView;


/**
 * author: ywj
 * created on: 2018/10/31 10:34
 * description: 无网络弹窗
 */
public class DisNetDialog extends BaseDialog {

    public DisNetDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int setContentView() {
        return R.layout.dialog_dis_net;
    }

    @Override
    protected void initView(View rootView) {
        setDialogWidth(0.8f);
        setCancelable(true);
        setCanceledOnTouchOutside(false);

        ShapeTextView tvCancle = rootView.findViewById(R.id.tv_cancle);
        tvCancle.setText("取消");
        ShapeTextView tvConfirm = rootView.findViewById(R.id.tv_confirm);
        tvConfirm.setText("设置");
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                getContext().startActivity(intent);
            }
        });
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onCancleClickListener != null) {
                    onCancleClickListener.onClick(v);
                }
            }
        });

    }

    private View.OnClickListener onCancleClickListener;

    public void setOnCancleClickListener(View.OnClickListener listener) {
        onCancleClickListener = listener;
    }

}