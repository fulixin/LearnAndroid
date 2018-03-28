package com.fu.learn.function.tableexecel.out;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.fu.learn.R;
import com.fu.learn.adapter.CommAdapter;

import java.util.ArrayList;

/**
 * Created by fulixin on 2018/3/28.
 */

public class TableExecelActivity extends Activity {
    private TableExecelLayout tableExecelLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_execel_out_activity_main);
        final ArrayList<TableEntity> datas = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            TableEntity tableEntity = new TableEntity();
            tableEntity.setBedNo("第" + i + "床");
            tableEntity.setName("小" + i);
            tableEntity.setAge("" + i);
            tableEntity.setTiwen("" + i);
            tableEntity.setMaibo("" + i);
            tableEntity.setHuxi("" + i);
            datas.add(tableEntity);
        }
        tableExecelLayout = findViewById(R.id.id_table_out_view);
        tableExecelLayout.setViewLeftId(R.layout.table_execel_item_left, R.layout.table_execel_item_right);
        tableExecelLayout.setAdapter(new CommAdapter<TableEntity>(TableExecelActivity.this) {

            @Override
            public ArrayList<TableEntity> getDatas() {
                return datas;
            }

            @Override
            public int getItemLayout() {
                return R.layout.table_execel_item_left;
            }

            @Override
            public void setViewValue(View view, TableEntity item) {
                ((TextView) view.findViewById(R.id.tv_left)).setText(item.getBedNo());
            }
        }, new CommAdapter<TableEntity>(TableExecelActivity.this) {
            @Override
            public ArrayList<TableEntity> getDatas() {
                return datas;
            }

            @Override
            public int getItemLayout() {
                return R.layout.table_execel_item_right;
            }

            @Override
            public void setViewValue(View view, TableEntity item) {
                ((TextView) view.findViewById(R.id.tv_name)).setText(item.getName());
                ((TextView) view.findViewById(R.id.tv_age)).setText(item.getAge());
                ((TextView) view.findViewById(R.id.tv_tiwen)).setText(item.getTiwen());
                ((TextView) view.findViewById(R.id.tv_maibo)).setText(item.getMaibo());
                ((TextView) view.findViewById(R.id.tv_huxi)).setText(item.getHuxi());
            }
        });
        tableExecelLayout.initView();
    }
}
