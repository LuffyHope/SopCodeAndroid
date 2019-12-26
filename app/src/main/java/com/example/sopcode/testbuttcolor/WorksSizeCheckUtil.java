package com.example.sopcode.testbuttcolor;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class WorksSizeCheckUtil {


    static IEditTextChangeListener mChangeListener;

    public static void setChangeListener(IEditTextChangeListener changeListener) {
        mChangeListener = changeListener;
    }

    public static class textChangeListener {
        private TextView button;
        private EditText[] editTexts;

        public textChangeListener(TextView button) {
            this.button = button;
        }

        public textChangeListener addAllEditText(EditText... editTexts) {
            this.editTexts = editTexts;
            initEditListener();
            return this;
        }


        private void initEditListener() {
            for (EditText editText : editTexts) {
                editText.addTextChangedListener(new textChange());
            }
        }


        /**
         * edit输入的变化来改变按钮的是否点击
         */
        private class textChange implements TextWatcher {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (checkAllEdit()) {
                    mChangeListener.textChange(true);
                    button.setEnabled(true);
                } else {
                    button.setEnabled(false);
                    mChangeListener.textChange(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        }

        private boolean checkAllEdit() {
            for (EditText editText : editTexts) {
                if (!TextUtils.isEmpty(editText.getText() + "")) {
                    continue;
                } else {
                    return false;
                }
            }
            return true;
        }
    }
}
