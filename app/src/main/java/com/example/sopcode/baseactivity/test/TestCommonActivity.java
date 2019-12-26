package com.example.sopcode.baseactivity.test;

import com.example.sopcode.R;
import com.example.sopcode.baseactivity.CommonActivity;
import com.example.sopcode.basefragment.test.TestFragment;
import com.example.sopcode.utils.ViewUtil;

public class TestCommonActivity extends CommonActivity {
    @Override
    protected String setTitleText() {
        return "hahahah";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_common;
    }

    @Override
    protected void init() {
        super.init();
        TestFragment fragment = new TestFragment();
        ViewUtil.replaceFragment(this,fragment,false,R.id.frame_layout);
    }
}
