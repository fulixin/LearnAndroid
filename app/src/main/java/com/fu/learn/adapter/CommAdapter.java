package com.fu.learn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by fulixin on 2018/3/2.
 */

public abstract class CommAdapter<T> extends BaseAdapter {
    private Context mContext;

    public CommAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public abstract ArrayList<T> getDatas();

    public abstract int getItemLayout();

    public abstract void setViewValue(View view, T item);

    @Override
    public int getCount() {
        return getDatas().size();
    }

    @Override
    public T getItem(int i) {
        return getDatas().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        return getItemViewType(getItem(position));
    }

    public int getItemViewType(T t) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(mContext).inflate(getItemLayout(), viewGroup, false);
        setViewValue(view, getItem(i));
        return view;
    }
}
