package com.recycleview.wxf.recycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private List<String> stringsList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
        stringsList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            stringsList.add("我是第"+i+"条数据");
        }
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rv.setLayoutManager(gridLayoutManager);
        myAdapter = new MyAdapter(this, stringsList);

        myAdapter.setItemOnClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, String data) {
//                Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();
//                myAdapter.addItem(position,"我是新加的数据");
                myAdapter.removeItem(position);
            }

        });
        rv.setAdapter(myAdapter);


    }
}
