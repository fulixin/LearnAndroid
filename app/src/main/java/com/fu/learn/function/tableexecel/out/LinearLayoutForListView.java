package com.fu.learn.function.tableexecel.out;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.fu.learn.adapter.CommAdapter;

/**
 *  取代ListView的LinearLayout，使之能够成功嵌套在ScrollView中
 * Created by fulixin on 2018/3/28.
 */

public class LinearLayoutForListView extends LinearLayout {
    private CommAdapter mAdapter;


    public LinearLayoutForListView(Context context) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);
    }

    public LinearLayoutForListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
    }

    public LinearLayoutForListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(LinearLayout.VERTICAL);
    }


    public void setAdapter(CommAdapter adapter) {
        if (adapter == null) {
            throw new NullPointerException("CustomAdapter is null, please check setAdapter(CustomAdapter adapter)...");
        }
        mAdapter = adapter;
//        adapter.setOnNotifyDataSetChangedListener(new CommAdapter.OnNotifyDataSetChangedListener() {
//            @Override
//            public void OnNotifyDataSetChanged() {
//                notifyDataSetChanged();
//            }
//        });
        adapter.notifyDataSetChanged();
    }

    public CommAdapter getAdapter() {
        return mAdapter;
    }

    private void notifyDataSetChanged() {
        removeAllViews();
        if (mAdapter == null || mAdapter.getCount() == 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < mAdapter.getCount(); i++) {
            final int index = i;
//            View view = mAdapter.getView(index);
//            if (view == null) {
//                throw new NullPointerException("item layout is null, please check getView()...");
//            }
//            addView(view, index, layoutParams);
        }
    }
}
