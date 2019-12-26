package com.example.sopcode.basebottomdialog;

import android.view.View;

import com.example.sopcode.R;

/**
 * BottomSheetDialogFragment 使用方法
 * 首先导入gradle
 * compile 'com.android.support:design:27.+'
 * <p>
 * 继承BaseBottomDialog 实现
 * getContentView()
 * init(View view)
 * onClick(View view)方法
 * <p>
 * 调用
 * TestBottomDialog bottom = TestBottomDialog.newInstance();
 * bottom.show(getSupportFragmentManager(), "tag");便可以展示了
 */
public class TestBottomDialog extends BaseBottomDialog {
    public static TestBottomDialog newInstance() {
        TestBottomDialog fragment = new TestBottomDialog();
        return fragment;
    }

    @Override
    protected int getContentView() {
        return R.layout.base_bottom_dialog;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    public void onClick(View view) {

    }
}