package com.example.sopcode.basedialog;

import android.view.View;
import android.widget.TextView;

import com.example.sopcode.R;
import com.example.sopcode.utils.EventHelper;

/**
 * 使用方法
 * 继承BaseDialog
 * 如果是全屏那么重写isFull()方法并返回true
 */
public class DialogTest extends BaseDialog {
    public static DialogTest newInstance() {
        DialogTest fragment = new DialogTest();
        return fragment;
    }

    @Override
    protected int getContentView() {
        return R.layout.dialog_binding;
    }

    @Override
    protected void init(View view) {
        TextView sure = view.findViewById(R.id.tv_sure);
        TextView giveUp = view.findViewById(R.id.give_up);
        TextView phoneNumber = view.findViewById(R.id.text_phone_number);
        EventHelper.click(this, sure, giveUp);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sure:
                dismiss();
                break;
            case R.id.give_up:
                break;
            case R.id.text_phone_number:
                break;
            default:
                break;
        }
    }
}
