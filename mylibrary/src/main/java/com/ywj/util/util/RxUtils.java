package com.ywj.util.util;

import android.app.Activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ywj.util.adapter.MyAdapter;
import com.ywj.util.base.IView;
import com.ywj.util.dialog.LoadingDialog;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RxUtils {

    private RxUtils() {
    }

    public static <T> Observable<T> load(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                ;
    }

    public static <T> Observable<T> loading(Observable<T> observable) {
        Activity context = ActivityStackUtil.getInstance().getCurrentActivity();

        LoadingDialog loadingDialog = new LoadingDialog(context);
        return observable
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    try {
                        if (loadingDialog != null && loadingDialog.getContext() != null) {
                            loadingDialog.show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    try {
                        if (loadingDialog != null && loadingDialog.getContext() != null) {
                            loadingDialog.cancel();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                ;
    }


    /**
     * 分页
     */
    public static <T> Observable<T> loadPartPage(Observable<T> observable, final IView view, boolean isRefresh, MyAdapter adapter, int pageSize) {
        return observable
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    try {
                        if (isRefresh) {
                            view.showLoading();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    try {
                        if (isRefresh) {
                            view.hideLoading();
                            if (adapter.getItemCount() < pageSize) {
                                adapter.loadMoreEnd();
                            }
                        } else {
                            if (adapter.getItemCount() < pageSize) {
                                adapter.loadMoreEnd();
                            } else {
                                adapter.loadMoreComplete();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

}
