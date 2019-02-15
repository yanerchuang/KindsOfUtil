package com.ywj.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.ywj.R;

import java.util.List;

public abstract class MyAdapter<T> extends BaseQuickAdapter<T, MyViewHolder> {
    protected Context mContext;

    public MyAdapter(Context context, int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        mContext = context;

    }

    public int getPosition(T item) {
        return getData().indexOf(item);
    }

    @Override
    public void setEnableLoadMore(boolean enable) {
        if (enable) {
            super.setLoadMoreView(new SimpleLoadMoreView());
        }
        super.setEnableLoadMore(enable);
    }


    public void setEmptyLoadingView() {
        setEmptyView(R.layout.adapter_loading_view, (ViewGroup) getRecyclerView().getParent());
    }

    public void setEmptyNoDataView() {
        setEmptyView(R.layout.adapter_empty_view, (ViewGroup) getRecyclerView().getParent());

    }

    public void setEmptyErrorView() {
        setEmptyView(R.layout.adapter_error_view, (ViewGroup) getRecyclerView().getParent());
    }

    public void setEmptyErrorView(View.OnClickListener onClickListener) {
        View view = LayoutInflater.from(((ViewGroup) getRecyclerView().getParent()).getContext()).inflate(R.layout.adapter_error_view, (ViewGroup) getRecyclerView().getParent(), false);
        view.setOnClickListener(onClickListener);
        setEmptyView(view);
    }
}
