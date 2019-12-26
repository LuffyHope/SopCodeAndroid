package com.example.sopcode.system_promise_summary.dialog;

import android.view.View;

import com.example.sopcode.R;
import com.example.sopcode.basedialog.BaseDialog;

public class OpenNotifyDialog extends BaseDialog {

    @Override
    protected int getContentView() {
        return R.layout.dialog_prompt_comment;
    }

    @Override
    protected void init(View view) {
        view.findViewById(R.id.bt_cancle).setOnClickListener(this);
        view.findViewById(R.id.bt_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_cancle:
                dismiss();
                break;
            case R.id.bt_button:
                if (callBack != null) {
                    callBack.sure();
                }
                break;
            default:
                break;
        }
    }

    public interface IDialogCallBack {
        void sure();
    }

    private IDialogCallBack callBack;

    public void setCallBack(IDialogCallBack callBack) {
        this.callBack = callBack;
    }
}


