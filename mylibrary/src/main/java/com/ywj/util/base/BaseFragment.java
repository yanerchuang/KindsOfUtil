package com.ywj.util.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ywj.util.dialog.DisNetDialog;
import com.ywj.util.dialog.LoadingDialog;
import com.ywj.util.util.DisPlayUtils;
import com.ywj.util.util.NetUtil;
import com.ywj.util.util.ToastUtil;
import com.ywj.util.R;
import com.ywj.util.util.LogUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * author: ywj
 * created on: 2018/10/31 16:26
 * description:
 */
public abstract class BaseFragment extends Fragment {
    public Context mContext;
    public boolean isInit;
    private Unbinder unbinder;
    private View contentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        contentView = inflater.inflate(setContentView(), null);
        unbinder = ButterKnife.bind(this, contentView);
        initView(contentView);
        initToolBar();
        isInit = true;
        initData();
        loadData();
        return contentView;
    }

    @LayoutRes
    protected abstract int setContentView();

    @Override
    public void onDestroy() {
        super.onDestroy();
        isInit = false;
        if (unbinder != null) {
            unbinder.unbind();
        }

        hideProgress();
    }


    protected abstract void initView(View contentView);

    protected abstract void initData();

    protected abstract void loadData();


    protected void showToast(String msg) {
        ToastUtil.show(mContext, msg);
    }

    protected void logD(String msg) {
        LogUtil.d(msg);
    }

    protected void logE(String msg) {
        LogUtil.e(msg);
    }


    private void initToolBar() {
        ImageView ivBack = (ImageView) contentView.findViewById(R.id.iv_back);
        if (ivBack != null) {
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentActivity activity = getActivity();
                    if (activity != null) {
                        activity.finish();
                    }
                }
            });
        }

    }

    protected void setToolbarTitle(String title) {
        TextView tvTitle = (TextView) contentView.findViewById(R.id.tv_title);
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
    }

    protected void hideToolbarLeftImage() {
        ImageView ivBack = (ImageView) contentView.findViewById(R.id.iv_back);
        if (ivBack != null) {
            DisPlayUtils.setViewGone(ivBack);
        }
    }

    protected void hideToolbarRightImage() {
        ImageView ivRight = (ImageView) contentView.findViewById(R.id.iv_right);
        if (ivRight != null) {
            DisPlayUtils.setViewGone(ivRight);
        }
    }

    protected void setToolbarLeftImage(@DrawableRes int resId, View.OnClickListener onClickListener) {
        ImageView ivBack = (ImageView) contentView.findViewById(R.id.iv_back);
        if (ivBack != null) {
            ivBack.setImageResource(resId);
            ivBack.setOnClickListener(onClickListener);
        }
    }

    protected void setToolbarRightImage(@DrawableRes int resId, View.OnClickListener onClickListener) {
        ImageView ivRight = (ImageView) contentView.findViewById(R.id.iv_right);
        if (ivRight != null) {
            ivRight.setImageResource(resId);
            ivRight.setOnClickListener(onClickListener);
        }
    }

    protected void setToolbarLeftText(String content, View.OnClickListener onClickListener) {
        TextView tvLeft = (TextView) contentView.findViewById(R.id.tv_left);
        if (tvLeft != null) {
            tvLeft.setText(content);
            DisPlayUtils.setViewVisible(tvLeft);
            tvLeft.setOnClickListener(onClickListener);
        }
        hideToolbarLeftImage();
    }

    protected void setToolbarRightText(String content, View.OnClickListener onClickListener) {
        TextView tvRight = (TextView) contentView.findViewById(R.id.tv_right);
        if (tvRight != null) {
            tvRight.setText(content);
            DisPlayUtils.setViewVisible(tvRight);
            tvRight.setOnClickListener(onClickListener);
        }
        hideToolbarRightImage();
    }

    protected void startActivity(Class<? extends Activity> cls) {
        mContext.startActivity(new Intent(mContext, cls));
    }


    public void showProgress() {
        LoadingDialog.show(mContext);
    }

    public void hideProgress() {
        LoadingDialog.cancle();
    }


    public boolean showNoNetView() {
        if (NetUtil.NETWORN_NONE == NetUtil.getNetworkState(mContext)) {
            new DisNetDialog(getActivity()).show();
            return true;
        }
        return false;
    }
}
