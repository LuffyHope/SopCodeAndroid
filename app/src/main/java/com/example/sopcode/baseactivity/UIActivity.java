package com.example.sopcode.baseactivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;

public abstract class UIActivity<B extends ViewDataBinding> extends AppCompatActivity {
    protected AppCompatActivity context;
    protected ImmersionBar immersionBar;
    protected B dataBind;
    protected Bundle savedInstanceState;

    public UIActivity() {
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        this.savedInstanceState = savedInstanceState;
        this.dataBind = DataBindingUtil.setContentView(this, this.getLayoutId());
        this.initScreen();
        this.brforeInit();
        this.init();
    }

    protected void brforeInit() {
    }

    protected void onResume() {
        super.onResume();
    }

    public void finish() {
        super.finish();
        hideSoftKeyboard(this.context);
    }

    protected final void initScreen() {
        this.immersionBar = ImmersionBar.with(this).keyboardEnable(true);
        this.immersionBar.init();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.immersionBar != null) {
            this.immersionBar.destroy();
        }
    }

    public static void hideSoftKeyboard(Context context) {
        View focus_view = ((Activity) context).getCurrentFocus();
        if (focus_view != null) {
            @SuppressLint("WrongConstant") InputMethodManager inputManager = (InputMethodManager) context.getSystemService("input_method");
            inputManager.hideSoftInputFromWindow(focus_view.getWindowToken(), 0);
        }

    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void init();
}
