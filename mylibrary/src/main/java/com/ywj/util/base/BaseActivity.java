package com.ywj.util.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ywj.util.dialog.DisNetDialog;
import com.ywj.util.dialog.LoadingDialog;
import com.ywj.util.util.DisPlayUtils;
import com.ywj.util.util.LoggerUtil;
import com.ywj.util.util.NetUtil;
import com.ywj.util.util.StatusBarUtils;
import com.ywj.util.util.ToastUtil;
import com.ywj.util.R;
import com.ywj.util.util.ActivityStackUtil;

import butterknife.ButterKnife;


/**
 * author: ywj
 * created on: 2018/10/31 10:36
 * description:
 */
public abstract class BaseActivity extends AppCompatActivity   {
    protected AppCompatActivity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        ActivityStackUtil.getInstance().pushActivity(mContext);

        StatusBarUtils.initStatusBar(mContext);

        setContentview();
        ButterKnife.bind(this);
        initToolBar();
        initView();
        if (NetUtil.NETWORN_NONE == NetUtil.getNetworkState(mContext)) {
            new DisNetDialog(mContext).show();
        }
        loadData();
//        EventBus.getDefault().register(this);
    }

    protected abstract void setContentview();

    protected abstract void initView();

    protected abstract void loadData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStackUtil.getInstance().popActivity(mContext);
        hideProgress();
//        EventBus.getDefault().unregister(this);
    }

    protected void showToast(String msg) {
        ToastUtil.show(mContext, msg);
    }

    protected void logD(String msg) {
        LoggerUtil.d(msg);
    }

    protected void logE(String msg) {
        LoggerUtil.e(msg);
    }

    private void initToolBar() {
        ImageView ivBack = (ImageView) findViewById(R.id.iv_back);
        if (ivBack != null) {
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

    }

    protected void setToolbarTitle(String title) {
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
    }

    protected void hideToolbarLeftImage() {
        ImageView ivBack = (ImageView) findViewById(R.id.iv_back);
        if (ivBack != null) {
            DisPlayUtils.setViewGone(ivBack);
        }
    }

    protected void hideToolbarRightImage() {
        ImageView ivRight = (ImageView) findViewById(R.id.iv_right);
        if (ivRight != null) {
            DisPlayUtils.setViewGone(ivRight);
        }
    }

    protected void setToolbarLeftImage(@DrawableRes int resId, View.OnClickListener onClickListener) {
        ImageView ivBack = (ImageView) findViewById(R.id.iv_back);
        if (ivBack != null) {
            ivBack.setImageResource(resId);
            ivBack.setOnClickListener(onClickListener);
        }
    }

    protected void setToolbarRightImage(@DrawableRes int resId, View.OnClickListener onClickListener) {
        ImageView ivRight = (ImageView) findViewById(R.id.iv_right);
        if (ivRight != null) {
            ivRight.setImageResource(resId);
            ivRight.setOnClickListener(onClickListener);
        }
    }

    protected void setToolbarLeftText(String content, View.OnClickListener onClickListener) {
        TextView tvLeft = (TextView) findViewById(R.id.tv_left);
        if (tvLeft != null) {
            tvLeft.setText(content);
            DisPlayUtils.setViewVisible(tvLeft);
            tvLeft.setOnClickListener(onClickListener);
        }
        hideToolbarLeftImage();
    }

    protected void setToolbarRightText(String content, View.OnClickListener onClickListener) {
        TextView tvRight = (TextView) findViewById(R.id.tv_right);
        if (tvRight != null) {
            tvRight.setText(content);
            DisPlayUtils.setViewVisible(tvRight);
            tvRight.setOnClickListener(onClickListener);
        }
        hideToolbarRightImage();
    }

    protected boolean isAnimFinish = true;

    @Override
    public void finish() {
        super.finish();
        if (isAnimFinish) {
            overridePendingTransition(R.anim.activity_exit_anim_in, R.anim.activity_exit_anim_out);
        }
    }

    protected void startActivity(Class<? extends Activity> cls) {
        mContext.startActivity(new Intent(mContext, cls));
        overridePendingTransition(R.anim.activity_start_anim_in, R.anim.activity_start_anim_out);
    }

    public void showProgress() {
        LoadingDialog.show(mContext);
    }

    public void hideProgress() {
        LoadingDialog.cancle();
    }


    public boolean showNoNetView() {
        if (NetUtil.NETWORN_NONE == NetUtil.getNetworkState(mContext)) {
            new DisNetDialog(this).show();
            return true;
        }
        return false;
    }

}

