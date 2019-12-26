package com.example.sopcode.floatview;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.sopcode.R;

/**
 * Created by Administrator on 2017/11/27.
 */

public class FloatPopupItem extends PopupWindow implements View.OnClickListener {
    private OnItemClickListener onItemClickListener;

    /**
     * 尼玛，这里这么简单就别写备注了，免得被人以为瞧不起拿刀砍
     *
     * @param context
     */
    public FloatPopupItem(Context context, int size, boolean status, boolean upOrDown) {
        //status ture 红色 false 蓝色；
        //upOrDown rue 向上展开  false 向下展开；
        View inflate = LayoutInflater.from(context).inflate(status ?
                (upOrDown ? R.layout.popupwindow1_top : R.layout.popupwindow1_bottom) :
                (upOrDown ? R.layout.popupwindow2_top : R.layout.popupwindow2_bottom), null, false);
        ImageView dismiss = inflate.findViewById(R.id.tv_dismiss);
        dismiss.setOnClickListener(this);
        TextView refresh = inflate.findViewById(R.id.tv_refresh);
        refresh.setOnClickListener(this);
        TextView goHome = inflate.findViewById(R.id.tv_go_home);
        goHome.setOnClickListener(this);
        setContentView(inflate);
        setWidth(size);
        setHeight(size * 4);
        setBackgroundDrawable(new ColorDrawable(0x00000000));
        setOutsideTouchable(false);
    }

    interface OnItemClickListener {
        void onItemClick(int i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.tv_dismiss:
                this.dismiss();
                break;
            case R.id.tv_refresh:
                onItemClickListener.onItemClick(1);
                break;
            case R.id.tv_go_home:
                onItemClickListener.onItemClick(2);
                break;
            default:
                break;
        }
    }
}
