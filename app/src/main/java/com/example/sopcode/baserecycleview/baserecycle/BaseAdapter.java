package com.example.sopcode.baserecycleview.baserecycle;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.MyHolder> {

    Context context;

    public BaseAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutItem(), parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.setDataView(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemCount();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public void setDataView(int position) {
            updataView(position);
        }

        public MyHolder(View itemView) {
            super(itemView);
            bindViewHolder(itemView);
        }
    }

    protected abstract @LayoutRes
    int getLayoutItem();

    protected abstract void bindViewHolder(View itemView);

    protected abstract void updataView(int position);

    protected abstract int itemCount();

    protected abstract void setList(List list);

    protected OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
