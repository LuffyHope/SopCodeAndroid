package com.example.sopcode.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.EditText;

public class MyEditText extends EditText {
    public MyEditText(Context context) {
        super(context);
        initView(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setEditTextInputSpace(this);
    }

    /**
     * 禁止EditText输入空格和换行符
     *
     * @param editText EditText输入框
     */
    public void setEditTextInputSpace(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(" ") || source.toString().contentEquals("\n")) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }
}
