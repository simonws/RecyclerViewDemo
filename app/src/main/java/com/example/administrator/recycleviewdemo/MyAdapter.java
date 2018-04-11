package com.example.administrator.recycleviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by yunansong on 2015/11/29 0029.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context mContext;
    private ArrayList<String> mData;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Integer> mHeights;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public MyAdapter(Context context, ArrayList<String> data) {
        mContext = context;
        mData = data;
        mLayoutInflater = LayoutInflater.from(context);

        mHeights = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            mHeights.add((int) (110 + Math.random() * 310));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //I think google force us to use viewholder mode
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
//        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
//        layoutParams.height = mHeights.get(position);
//        holder.itemView.setLayoutParams(layoutParams);

        holder.alpha.setText(mData.get(position));

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, layoutPos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, layoutPos);
                    return false;
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.my_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    public void add(int p) {
        mData.add(p, "add");
        notifyItemInserted(p);
    }

    public void delete(int p) {
        mData.remove(p);
        notifyItemRemoved(p);
    }
}

