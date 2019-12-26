package com.example.sopcode.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sopcode.App;
import com.example.sopcode.R;

/**
 * Toast统一管理类
 */
public class ToastUtils {

    private ViewGroup.LayoutParams layoutParams;

    private ToastUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(String message) {
        if (isShow)
            Toast.makeText(App.appContext, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        if (isShow)
            Toast.makeText(App.appContext, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

    /**
     * 设置toast显示的位置，这是居中
     *
     *
     */

    public static void showToast(Context ctx, String content) {
        Toast mToast = new Toast(ctx);
        mToast.setGravity(Gravity.CENTER, 0, 0);//设置toast显示的位置，这是居中
        mToast.setDuration(Toast.LENGTH_SHORT);//设置toast显示的时长
        View _root = LayoutInflater.from(ctx).inflate(R.layout.toast_custom_common, null);//自定义样式，自定义布局文件
        TextView mTvToast = _root.findViewById(R.id.tvCustomToast);
        mToast.setView(_root);//设置自定义的view
        mTvToast.setText(content);//设置文本
        mToast.show();//展示toast
    }


}