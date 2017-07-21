package com.bwie.chaxboxdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Bean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_item);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewHolder holder = (ViewHolder) view.getTag();
                holder.checkBox.setChecked(!holder.checkBox.isChecked());
            }
        });
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Bean bean = new Bean();
            bean.name = "条目-" + i;
            list.add(bean);
        }
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                if (convertView == null) {
                    holder=new ViewHolder();
                    convertView = View.inflate(MainActivity.this, R.layout.item, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.name);
                    holder.checkBox = (CheckBox) convertView.findViewById(R.id.check);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                final Bean bean = list.get(position);
                holder.textView.setText(bean.name);

                holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        bean.isCheck = isChecked;
                    }
                });
                holder.checkBox.setChecked(bean.isCheck);
                return convertView;
            }
        });
    }

    class ViewHolder {
        TextView textView;
        CheckBox checkBox;
    }
}
