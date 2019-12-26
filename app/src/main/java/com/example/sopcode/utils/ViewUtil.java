package com.example.sopcode.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.sopcode.utils.activity_manager.ActivityManager;
import com.gyf.barlibrary.ImmersionBar;


public class ViewUtil {

    private static final int MIN_CLICK_DELAY_TIME = 500;
    private static long lastClickTime;
    private static Handler mHandler = new Handler();

    /**
     * 防止高频点击
     *
     * @return true 高频点击状态
     */
    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) <= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    /**
     * 关闭软件盘
     */
    public static void closeSoftKeyboard(final Context context) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    Activity activity;
                    if (context instanceof Activity) {
                        activity = (Activity) context;
                    } else {
                        activity = ActivityManager.getTagActivity();
                    }
                    if (activity == null) return;
                    View view = activity.getWindow().peekDecorView();
                    if (view != null) {
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                } catch (Throwable e) {
                }
            }
        }, 100);
    }

    /**
     * 自动打开软键盘
     */
    public static void openSoftKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, 0);
    }

    public static void showSoftKeyboard(Context context, View v) {
        v.requestFocus();
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(v, InputMethodManager.RESULT_SHOWN);
    }

    public static void addFragment(FragmentActivity activity, Fragment fragment,
                                   boolean isAddBack, @IdRes int container) {
        if (fragment == null)
            return;
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (fm.findFragmentByTag(fragment.getClass().getSimpleName()) != null) {
            ft.show(fm.findFragmentByTag(fragment.getClass().getSimpleName())).commit();
            return;
        }
        ft.add(container, fragment, fragment.getClass().getSimpleName());
        if (isAddBack) {
            ft.addToBackStack(fragment.getClass().getSimpleName());
        }
        ft.commit();
    }

    public static void replaceFragment(FragmentActivity activity, Fragment fragment,
                                       boolean isAddBack, @IdRes int container) {
        if (fragment == null)
            return;
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (fm.findFragmentByTag(fragment.getClass().getSimpleName()) != null) {
            ft.show(fm.findFragmentByTag(fragment.getClass().getSimpleName())).commit();
            return;
        }
        ft.replace(container, fragment, fragment.getClass().getSimpleName());
        if (isAddBack) {
            ft.addToBackStack(fragment.getClass().getSimpleName());
        }
        ft.commit();
    }

    public static void removeFragment(FragmentActivity activity, Fragment fragment) {
        if (fragment == null || fragment.isRemoving())
            return;
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (fragment.isAdded()) {
            ft.remove(fragment);
            ft.commit();
        }
    }

    public static void showFragment(FragmentActivity activity, Fragment fragment) {
        if (fragment == null || !fragment.isHidden())
            return;
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (fragment.isAdded()) {
            ft.show(fragment);
            ft.commit();
        }
    }

    public static void hideFragment(FragmentActivity activity, Fragment fragment) {
        if (fragment == null || fragment.isHidden())
            return;
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (fragment.isAdded()) {
            ft.hide(fragment);
            ft.commit();
        }
    }

    public static void immersionBar(ImmersionBar statusBar, View viewStatus) {
        immersionBar(statusBar, viewStatus, true);
    }

    public static void immersionBar(final ImmersionBar statusBar, final View viewStatus, final boolean isDarkFont) {
        if (viewStatus == null || statusBar == null) return;
        viewStatus.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isDarkFont) {
                    statusBar.statusBarView(viewStatus).statusBarDarkFont(true).init();
                } else {
                    statusBar.statusBarView(viewStatus).init();
                }
            }
        }, 50);
    }
}
