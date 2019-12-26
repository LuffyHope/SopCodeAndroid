package com.example.sopcode.baserecycleview.zhuangbirecycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter2<M, T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {
    protected List<M> mList;
    protected Context mCtx;

    BaseAdapter2(Context context) {
        mCtx = context;
    }

    @NonNull
    @Override
    public T onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<T> clazz = (Class<T>) type.getActualTypeArguments()[1];
        View itemView = LayoutInflater.from(mCtx).inflate(getItemView(), null, false);
        try {
            if (clazz.isMemberClass()) {
                String modifier = Modifier.toString(clazz.getModifiers());
                if (!modifier.contains("static")) {
                    Constructor<?>[] cons = clazz.getDeclaredConstructors();
                    Class<?>[] paramTypes = cons[0].getParameterTypes();
                    return (T) cons[0].newInstance(paramTypes[0].newInstance(), itemView);
                }
            }
            Constructor<?> constructor = clazz.getConstructor(View.class);
            Object o = constructor.newInstance(itemView);
            return (T) o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull T holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    public List<M> getList() {
        return mList;
    }

    public void setList(List<M> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void addList(List<M> list) {
        if (mList == null)
            mList = new ArrayList<>();
        if (list != null)
            mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    protected abstract int getItemView();

    protected OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
