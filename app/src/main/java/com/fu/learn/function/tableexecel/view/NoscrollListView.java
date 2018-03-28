package com.fu.learn.function.tableexecel.view;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
/**
 * 自定义实现ListView让其能跟着ScrollView滚动
 * Created by fulixin on 2018/3/27.
 */

public class NoscrollListView  extends ListView {

    public NoscrollListView(Context context) {
        super(context);
    }

    public NoscrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoscrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}