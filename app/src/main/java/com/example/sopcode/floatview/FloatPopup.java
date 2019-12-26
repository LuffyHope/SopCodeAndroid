package com.example.sopcode.floatview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.example.sopcode.R;
import com.example.sopcode.floatview.FloatPopupItem;


/**
 * Created by Administrator on 2017/11/27.
 */

public class FloatPopup extends PopupWindow implements FloatPopupItem.OnItemClickListener {

    //设置悬浮按钮尺寸
    private int size;
    private int margin = Util.dp2px(5);
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    //触发移动事件的最小距离
    private int touchSlop = new ViewConfiguration().getScaledTouchSlop();
    //记录当前手指实时位置
    private float curX, curY;
    //记录手指按下时的位置
    private float lastX, lastY;
    //设置当前悬浮按钮的显示位置
    private float showX, showY;
    //记录当前悬浮按钮显示位置
    private FloatPopupItem item;
    private Activity context;
    private OnClickListener onClickListener;

    private static FloatPopup floatPopup;

    public static FloatPopup getInstance(Context context, boolean status) {
        if (floatPopup == null) {
            floatPopup = new FloatPopup(context, Util.dp2px(40), status);
        }
        return floatPopup;
    }

    public void show() {
        if (!floatPopup.isShowing()) {
            WindowManager wm1 = context.getWindowManager();
            int width1 = wm1.getDefaultDisplay().getWidth() - size - 30;
            int height1 = wm1.getDefaultDisplay().getHeight() / 2 - size;
            floatPopup.showAtLocation(context.getWindow().getDecorView(),
                    Gravity.NO_GRAVITY, width1, height1);
            floatPopup.setOnClickListener((OnClickListener) context);
        }
    }

    private int barHeight = -1;

    private void init(Context context) {
        //获得导航栏的高度
        int resourceId = context.getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            barHeight = context.getApplicationContext().getResources().getDimensionPixelSize(resourceId);
        }
    }

    @SuppressLint("HandlerLeak")
    public FloatPopup(final Context context, final int size, final boolean status) {
        //status ture 红色 false 蓝色；
        init(context);
        this.size = size;
        this.context = (Activity) context;
        ImageView iv = new ImageView(context);
        iv.setMinimumWidth(size);
        iv.setMinimumHeight(size);
        iv.setImageDrawable(context.getResources().getDrawable(status ? R.mipmap.icon_big_f : R.mipmap.icon_big_blue));
        setContentView(iv);
        setWidth(size);
        setHeight(size);
        setFocusable(false);
        setBackgroundDrawable(new ColorDrawable(0x00000000));
        setOutsideTouchable(false);
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                curX = event.getRawX();
                curY = event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = event.getRawX();
                        lastY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //解决android手机头部的被覆盖的情况；
                        if (curY < showY || curY > (showY + size + barHeight)) {
                            if (curY < (barHeight + size / 2 + 10)) {
                                update((int) curY - size / 2, (barHeight + size / 2 + 10));
                            } else if (curY > screenHeight - size / 2) {
                                update((int) curX - size / 2, screenHeight - barHeight);
                            } else {
                                update((int) curX - size / 2, (int) curY - barHeight);
                            }
                        }
                        Log.d("==--==--==-==", "onTouch: curY = " + curY +
                                "\n event.getRawY() = " + event.getRawY() +
                                "\n lastY = " + lastY);
                        if (item != null && item.isShowing()) {
                            item.dismiss();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        //这里的30和show()方法中的30是为了不让按钮跑到太靠近侧边，以免有些手机是曲面的点击不到。
                        if (curX < screenWidth / 2) {
                            showX = 30;
                        } else {
                            showX = screenWidth - size - 30;
                        }
                        //解决android手机头部的被覆盖的情况；
                        if (event.getRawY() < (barHeight + size / 2 + 10)) {
                            showY = barHeight + size / 2 + 10;
                        } else if (event.getRawY() < showY || event.getRawY() > (showY + size + barHeight)) {
                            showY = event.getRawY() - size;

                        }
                        boolean upOrDown = true; //upOrDown rue 向上展开  false 向下展开；
                        if (showY > (size * 4)) {
                            upOrDown = true;
                        } else {
                            upOrDown = false;
                        }
                        update((int) showX, (int) showY);
                        float dx = lastX - curX;
                        float dy = lastY - curY;
                        if (Math.abs(dx) < touchSlop && Math.abs(dy) < touchSlop) {
                            showMenu(status, upOrDown);
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void hideMenu() {
        if (item != null) {
            item.dismiss();
        }
    }

    private void showMenu(boolean status, boolean upOrDown) {
        item = new FloatPopupItem(context, size, status, upOrDown);
        item.setOnItemClickListener(this);
        item.showAtLocation(context.getWindow().getDecorView(), Gravity.NO_GRAVITY, (int) (showX), upOrDown ? ((int) showY - (size * 3)) :
                (int) showY);
    }

    @Override
    public void update(int x, int y) {
        this.update(x, y, size, size);
    }

    @Override
    public void update(int x, int y, int width, int height) {
        super.update(x, y, width, height);
    }


    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        showY = y;
    }

    /**
     * 这里用了两个接口把子项item的点击事件传到FloatPopup的onClick(int i)方法里面统一处理，
     * 因为我们只对外暴露FloatPopup
     *
     * @param i
     */
    @Override
    public void onItemClick(int i) {
        if (onClickListener != null) {
            onClickListener.onClick(i);
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int i);
    }

    public void release() {
        dismiss();
        floatPopup = null;
        if (item != null)
            item.dismiss();
    }
}
