package com.recycleview.wxf.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wxf on 2016/11/10.
 */
public class MyAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private OnItemClickListener listener;
    private Context context;
    private List<String> strList;
    private RecyclerView recyclerView;

    public void setItemOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public MyAdapter(Context context, List<String> strList) {
        this.context = context;
        this.strList = strList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycleview_item, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tv.setText(strList.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    @Override
    public int getItemCount() {
        return strList.size();
    }

    @Override
    public void onClick(View v) {
        if (recyclerView != null && listener != null) {
            int position = recyclerView.getChildAdapterPosition(v);
            if (position > -1) {
                listener.onItemClick(recyclerView, v, position, strList.get(position));
            }

        }

    }

    public void addItem(int position, String data) {
        strList.add(position, data);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        if (position > -1) {
            strList.remove(position);
            notifyItemRemoved(position);
        }

    }


    public interface OnItemClickListener {
        void onItemClick(RecyclerView parent, View view, int position, String data);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);

        }
    }
}
