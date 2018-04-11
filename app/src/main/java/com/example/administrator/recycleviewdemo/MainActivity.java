package com.example.administrator.recycleviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private RecyclerView mRecycleView;
    private ArrayList<String> mData;
    private MyAdapter mAdapter;
    private Button mDeleteButton;
    private Button mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        mDeleteButton = (Button)findViewById(R.id.delete);
        mAddButton = (Button)findViewById(R.id.add);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.delete(1);
            }
        });
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.add(1);
            }
        });

        mData = new ArrayList<>();
        initMyData();

        mRecycleView = (RecyclerView)findViewById(R.id.myRecycleView);
        mAdapter = new MyAdapter(this, mData);
        mRecycleView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), position+" click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), position+" long click", Toast.LENGTH_SHORT).show();
            }
        });

        //here is vertical listview mode
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycleView.setLayoutManager(linearLayoutManager);

        //Here is gridview mode
//        mRecycleView.setLayoutManager(new GridLayoutManager(this, 3));

        //Here is horizontal gridview
//        mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));

        //生成瀑布流，  waterfall layout
//        mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        mRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    private void initMyData(){
        for(int i = 'A'; i <= 'z'; i++){
            mData.add(""+ (char)i); //convert int to String
        }
    }
}
