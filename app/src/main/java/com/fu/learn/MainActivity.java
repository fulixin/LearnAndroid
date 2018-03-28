package com.fu.learn;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.fu.learn.adapter.CommAdapter;
import com.fu.learn.entity.MainListEntity;
import com.fu.learn.function.tableexecel.TableExecelMainActivity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView id_listview;
    private ArrayList<MainListEntity> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeLayout();
        initializeData();
        viewOnClick();
    }

    private void initializeData() {
        try {
            InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open("main-list-data.json"));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String result = "";
            while ((line = bufReader.readLine()) != null)
                result += line;
            System.out.printf("返回数据:" + result);
            JsonParser jsonParser = new JsonParser();
            datas = new ArrayList<>();
            for (JsonElement jsonElement : jsonParser.parse(result).getAsJsonArray()) {
                Gson gson = new Gson();
                datas.add(gson.fromJson(jsonElement, MainListEntity.class));
            }
            id_listview.setAdapter(new CommAdapter<MainListEntity>(MainActivity.this) {
                @Override
                public ArrayList<MainListEntity> getDatas() {
                    return datas;
                }

                @Override
                public int getItemLayout() {
                    return R.layout.adapte_mainlist_item;
                }

                @Override
                public void setViewValue(View view, MainListEntity item) {
                    TextView textView = view.findViewById(R.id.id_adapter_mainlist_tv);
                    textView.setText(item.getTitle());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewOnClick() {
        id_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ComponentName componentName=new ComponentName(getPackageName(),datas.get(i).getClassName());
                Intent intent=new Intent();
                intent.setComponent(componentName);
                startActivity(intent);
            }
        });
    }

    private void initializeLayout() {
        id_listview = (ListView) findViewById(R.id.id_main_listview);
    }
}
