package com.example.sopcode.basefragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.sopcode.utils.ViewUtil;
import com.gyf.barlibrary.ImmersionBar;

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {
    protected boolean isVisible;
    protected boolean isPrepared;
    protected boolean isLoaded;
    protected View contentView;
    protected T binding;
    protected ImmersionBar immersionBar;

    public BaseFragment() {
    }

    @Nullable
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getContentView(), container, false);
        contentView = binding.getRoot();
        isPrepared = true;
        initScreen();
        init();
        onVisible();
        return contentView;
    }

    protected final void initScreen() {
        immersionBar = ImmersionBar.with(this).keyboardEnable(true);
        immersionBar.init();
    }

    protected void showKeyboard(final EditText editText) {
        (new Handler()).postDelayed(new Runnable() {
            public void run() {
                ViewUtil.showSoftKeyboard(BaseFragment.this.getContext(), editText);
            }
        }, 100L);
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (immersionBar != null) {
            immersionBar.destroy();
        }

    }

    private void onVisible() {
        if (isPrepared || isVisible) {
            lazyLoadEvery();
            if (!isLoaded) {
                isLoaded = true;
                lazyLoad();
            }
        }
    }

    protected abstract void lazyLoad();

    protected void lazyLoadEvery() {
    }

    protected abstract int getContentView();

    protected void onInvisible() {
        try {
            ViewUtil.closeSoftKeyboard(getContext());
        } catch (Exception var2) {
            ;
        }
    }

    protected abstract void init();
}
