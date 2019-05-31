package com.ywj.util.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.ywj.util.R;
import com.ywj.util.view.ShapeTextView;


public class ConfirmDialog extends BaseDialog {

    private int contentGravity;

    private String title = "温馨提示";
    private String content = "";
    private String cancleText = "取消";
    private String confimText = "确定";

    private boolean isOnlyShowOne;//是否只显示一个按钮

    private OnClickListener onClickListener;

    public ConfirmDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int setContentView() {
        return R.layout.dialog_layout_confirm;
    }

    @Override
    protected void initView(View rootView) {
        //设置宽度
        setDialogWidth(0.8f);
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        //title content
        TextView tvTitle = rootView.findViewById(R.id.tv_title);
        TextView tvContent = rootView.findViewById(R.id.tv_content);

        tvTitle.setText(title);
        tvContent.setText(content);
        if (contentGravity != 0) {
            tvContent.setGravity(contentGravity);
        } else {
            tvContent.setGravity(Gravity.CENTER);
        }


        //只显示一个按钮 or 显示取消、确定按钮
        if (isOnlyShowOne) {
            rootView.findViewById(R.id.ll_cancle_confim_container).setVisibility(View.GONE);
            ShapeTextView tvOk = rootView.findViewById(R.id.tv_ok);
            tvOk.setText(confimText);
            tvOk.setVisibility(View.VISIBLE);
            tvOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onConfirmClick();
                        dismiss();
                    }
                }
            });
        } else {
            ShapeTextView tvCancle = rootView.findViewById(R.id.tv_cancle);
            ShapeTextView tvConfirm = rootView.findViewById(R.id.tv_confirm);

            tvCancle.setText(cancleText);
            tvCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        dismiss();
                        onClickListener.onCancleClick();
                    }
                }
            });
            tvConfirm.setText(confimText);
            tvConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        dismiss();
                        onClickListener.onConfirmClick();
                    }
                }
            });
        }

    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setContentGravity(int gravity) {
        contentGravity = gravity;
    }

    public void setCancleText(String cancleText) {
        this.cancleText = cancleText;
    }

    public void setConfimText(String confimText) {
        this.confimText = confimText;
    }

    public void setOnlyOneButton(String confirmContent) {
        this.isOnlyShowOne = true;
        this.confimText = confirmContent;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onCancleClick();

        void onConfirmClick();
    }
}
