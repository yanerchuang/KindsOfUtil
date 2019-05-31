package com.ywj.util.net;

import android.support.annotation.NonNull;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

public abstract class ErrorHandleSubscriber<T> implements Observer<T> {

    public ErrorHandleSubscriber() {
    }


    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }


    @Override
    public void onComplete() {

    }


    @Override
    public void onError(@NonNull Throwable t) {
        t.printStackTrace();
        //如果你某个地方不想使用全局错误处理,则重写 onError(Throwable) 并将 super.onError(e); 删掉
        //如果你不仅想使用全局错误处理,还想加入自己的逻辑,则重写 onError(Throwable) 并在 super.onError(e); 后面加入自己的逻辑
        RxErrorHandler rxErrorHandler = RetrofitManager.getRxErrorHandler();
        if (rxErrorHandler != null) {
            rxErrorHandler.getHandlerFactory().handleError(t);
        }
    }
}