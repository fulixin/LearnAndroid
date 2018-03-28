package com.fu.learn.function.tableexecel;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.fu.learn.R;
import com.fu.learn.function.tableexecel.fragment.ItemFragment;
import com.fu.learn.function.tableexecel.view.NoscrollListView;
import com.fu.learn.function.tableexecel.view.SyncHorizontalScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 仿execel表格，实现左右滑动，上下滑动，都能保持第一列和第一行不动
 * 摘自https://blog.csdn.net/xujingqing/article/details/70681027
 * 感谢大神分享
 * Created by fulixin on 2018/3/27.
 */

public class TableExecelMainActivity extends Activity {
    private NoscrollListView mLeft;
    private LeftAdapter mLeftAdapter;

    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;
    private List<String> mListData;
    private ArrayList<HashMap<String, String>> data;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.table_execel_activity_main);
        initView();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();
        fragments = new ArrayList<Fragment>();
        for (int i = 0; i < mListData.size(); i++) {
            ItemFragment mFOne = new ItemFragment();
            fragments.add(mFOne);
        }
        Log.i("TAG", "fragment.size==" + fragments.size());
        for (int i = 0; i < fragments.size(); i++) {
            tx.add(R.id.lv_data, fragments.get(i));
        }
        tx.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (int i = 0; i < fragments.size(); i++) {
            ItemFragment fragment = (ItemFragment) fragments.get(i);
            fragment.setXm(data.get(i).get("姓名" + i));
            fragment.setNl(data.get(i).get("年龄" + i));
            fragment.setTw(data.get(i).get("体温" + i));
            fragment.setMb(data.get(i).get("脉搏" + i));
            fragment.setHx(data.get(i).get("呼吸" + i));
        }

    }

    protected void save() {
        for (int i = 0; i < fragments.size(); i++) {
            String string = ((ItemFragment) fragments.get(i)).getValue();
            Log.i("TAG", "string==" + string);

        }

    }

    private void initView() {
        mLeft = (NoscrollListView) findViewById(R.id.lv_left);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);

        mDataHorizontal.setScrollView(mHeaderHorizontal);
        mHeaderHorizontal.setScrollView(mDataHorizontal);

        mListData = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            mListData.add("" + i);
        }

        mLeftAdapter = new LeftAdapter();
        mLeft.setAdapter(mLeftAdapter);
        setData();
    }

    private void setData() {
        if (data == null) {
            data = new ArrayList<>();

        }
        for (int i = 0; i < mListData.size(); i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("姓名" + i, i + "姓名");
            map.put("年龄" + i, i + "年龄");
            map.put("体温" + i, i + "体温");
            map.put("脉搏" + i, i + "脉搏");
            map.put("呼吸" + i, i + "呼吸");
            data.add(map);

        }

    }

    class LeftAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(TableExecelMainActivity.this).inflate(R.layout.table_execel_item_left, null);
                holder.tvLeft = (TextView) convertView.findViewById(R.id.tv_left);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvLeft.setText("第" + position + "床");

            return convertView;
        }

        class ViewHolder {
            TextView tvLeft;
        }
    }

    ArrayList<ArrayList<EditText>> list;


}