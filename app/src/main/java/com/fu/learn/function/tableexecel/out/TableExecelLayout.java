package com.fu.learn.function.tableexecel.out;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.fu.learn.adapter.CommAdapter;
import com.fu.learn.function.tableexecel.view.NoscrollLinearLayout;
import com.fu.learn.function.tableexecel.view.NoscrollListView;
import com.fu.learn.function.tableexecel.view.SyncHorizontalScrollView;
import com.linearlistview.LinearListView;

/**
 * Created by fulixin on 2018/3/28.
 */

public class TableExecelLayout extends RelativeLayout {
    private NoscrollLinearLayout noscrollLinearLayoutData;
    private NoscrollListView noscrollListViewLeft;
    private SyncHorizontalScrollView syncHorizontalScrollViewHeader;
    private SyncHorizontalScrollView syncHorizontalScrollViewData;

    private int viewLeftId, viewDataId;
    private CommAdapter adapterLeft, adapterData;

    public TableExecelLayout(Context context) {
        super(context);
    }

    public TableExecelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TableExecelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setViewLeftId(int idLeft, int idData) {
        viewLeftId = idLeft;
        viewDataId = idData;
    }

    public void setAdapter(CommAdapter adapterLeft, CommAdapter adapterData) {
        this.adapterLeft = adapterLeft;
        this.adapterData = adapterData;
    }

    private int getViewLeftId() {
        return viewLeftId;
    }

    private int getViewDataId() {
        return viewDataId;
    }

    private CommAdapter getAdapterLeft() {
        return adapterLeft;
    }

    private CommAdapter getAdapterData() {
        return adapterData;
    }

    public void initView() {
        View viewHeaderLeft = LayoutInflater.from(getContext()).inflate(getViewLeftId(), null);
        View viewHeaderData = LayoutInflater.from(getContext()).inflate(getViewDataId(), null);
        ScrollView scrollView = new ScrollView(getContext());
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearListView listView = new LinearListView(getContext());
        listView.setOrientation(LinearLayout.VERTICAL);
        listView.setAdapter(this.getAdapterData());
        syncHorizontalScrollViewHeader = new SyncHorizontalScrollView(getContext());
        syncHorizontalScrollViewHeader.addView(viewHeaderData);
        noscrollListViewLeft = new NoscrollListView(getContext());
        noscrollListViewLeft.setAdapter(this.getAdapterLeft());
        syncHorizontalScrollViewData = new SyncHorizontalScrollView(getContext());
        syncHorizontalScrollViewHeader.setScrollView(syncHorizontalScrollViewData);
        syncHorizontalScrollViewData.setScrollView(syncHorizontalScrollViewHeader);
        syncHorizontalScrollViewData.addView(listView);
        LayoutParams layoutParamsHeaderLeft = new LayoutParams(100, 50);
        LayoutParams layoutParamsHeaderData = new LayoutParams(LayoutParams.MATCH_PARENT, 50);
        layoutParamsHeaderData.setMargins(100, 0, 0, 0);
        LayoutParams layoutParamsData = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutParamsData.setMargins(0, 60, 0, 0);
        addView(viewHeaderLeft, layoutParamsHeaderLeft);
        addView(syncHorizontalScrollViewHeader, layoutParamsHeaderData);
        linearLayout.addView(noscrollListViewLeft,layoutParamsHeaderLeft);
        linearLayout.addView(syncHorizontalScrollViewData);
        scrollView.addView(linearLayout);
        addView(scrollView,layoutParamsData);

        syncHorizontalScrollViewHeader.setVerticalScrollBarEnabled(false);
        noscrollListViewLeft.setVerticalScrollBarEnabled(false);
        syncHorizontalScrollViewData.setVerticalScrollBarEnabled(false);
        invalidate();
    }
}
