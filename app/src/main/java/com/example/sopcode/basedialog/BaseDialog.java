package com.example.sopcode.basedialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment2;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public abstract class BaseDialog extends DialogFragment2 implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);//触碰背景不cancle
        View view = inflater.inflate(getContentView(), container, false);
        Window dialog = getDialog().getWindow();
        dialog.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setLayout(-1, -2);
        this.getDialog().setCanceledOnTouchOutside(false);
        init(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //如果设置为全屏：
        if (isFull()) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            getDialog().getWindow().setLayout(dm.widthPixels, dm.heightPixels);
        }
    }

    protected boolean isFull() {
        return false;
    }

    protected abstract int getContentView();

    protected abstract void init(View view);


    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, getClass().getSimpleName());
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            //在每个add事务前增加一个remove事务，防止连续的add
            manager.beginTransaction().remove(this).commit();
            super.show(manager, tag);
        } catch (Exception e) {
            //同一实例使用不同的tag会异常,这里捕获一下
            e.printStackTrace();
        }
    }
}
