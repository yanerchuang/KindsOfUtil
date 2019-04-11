package com.ywj.util.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;

import java.util.List;

public abstract class MyMultiItemAdapter<T extends MyMultiItemEntity> extends BaseMultiItemQuickAdapter<T  ,MyViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MyMultiItemAdapter(List<T> data) {
        super(data);
    }
}
