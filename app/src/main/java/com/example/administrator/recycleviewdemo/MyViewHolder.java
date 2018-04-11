package com.example.administrator.recycleviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yunansong on 2015/11/29 0029.
 */
class MyViewHolder extends RecyclerView.ViewHolder{
    TextView alpha;
    public MyViewHolder(View arg){
        super(arg);

        alpha = (TextView)arg.findViewById(R.id.my_alpha);
    }
}