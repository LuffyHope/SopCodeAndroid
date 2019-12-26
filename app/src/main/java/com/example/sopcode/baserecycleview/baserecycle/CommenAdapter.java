package com.example.sopcode.baserecycleview.baserecycle;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;


public abstract class CommenAdapter<T> extends BaseAdapter {

    public CommenAdapter(Context context) {
        super(context);
    }

    @Override
    protected int itemCount() {
        return mList.size();
    }

    List<T> mList;

    @Override
    public void setList(List list) {
        mList = list;
    }

    protected List<T> getList() {
        return mList;
    }

    protected void addList(List<T> list) {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        if (list != null) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }
}
