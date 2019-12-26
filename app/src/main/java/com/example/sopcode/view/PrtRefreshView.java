package com.example.sopcode.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.andview.refreshview.XRefreshView;

/**
 * 用法 依赖compile 'com.huxq17.xrefreshview:xrefreshview:3.6.9'
 * 重新包裹一层解决左右和上下滑动的冲突；
 */
public class PrtRefreshView  extends XRefreshView {

    private float mDownX;
    private float mDownY;

    public PrtRefreshView(Context context) {
        super(context);
    }

    public PrtRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getX();
                mDownY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getX();
                float moveY = ev.getRawY();
                float diffX = Math.abs(moveX - mDownX);
                float diffY = Math.abs(moveY - mDownY);
                boolean isHorizon = Math.tan(diffY / diffX) < Math.tan(Math.toRadians(45.0));
                if (isHorizon) {
                    return dispatchTouchEventSupper(ev);
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}

