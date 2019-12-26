package com.example.sopcode.basebottomdialog;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * BottomSheetDialogFragment 使用方法
 * 首先导入gradle
 * compile 'com.android.support:design:27.+'
 * <p>
 * 调用
 * TestBottomDialog bottom = TestBottomDialog.newInstance();
 * bottom.show(getSupportFragmentManager(), "tag");便可以展示了
 */
public abstract class BaseBottomDialog extends BottomSheetDialogFragment implements View.OnClickListener {

    private View view;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除缓存View和当前ViewGroup的关联
        ((ViewGroup) (view.getParent())).removeView(view);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (!isAdded()) return null;
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
        view = LayoutInflater.from(getActivity()).inflate(getContentView(), null, false);
        init(view);
        bottomSheetDialog.setContentView(view);
        View parent = (View) view.getParent();
        BottomSheetBehavior<View> mBehavior = BottomSheetBehavior.from(parent);
        mBehavior.setPeekHeight(1000);
        mBehavior.setHideable(true);
        parent.setBackgroundColor(Color.TRANSPARENT);
        return bottomSheetDialog;
    }

    protected abstract int getContentView();

    protected abstract void init(View view);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);//触碰背景不cancle  这行代码不能放在 上面的方法中 否则会崩溃
        return super.onCreateView(inflater, container, savedInstanceState);
    }

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
