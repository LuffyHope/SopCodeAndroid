package com.example.sopcode.basefragment.test;

import com.example.sopcode.R;
import com.example.sopcode.basefragment.BaseFragment;
import com.example.sopcode.databinding.FragmentTesBinding;
import com.example.sopcode.utils.ViewUtil;

public class TestFragment extends BaseFragment<FragmentTesBinding> {
    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_tes;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void lazyLoadEvery() {
        ViewUtil.immersionBar(immersionBar, binding.titleBarView);
    }
}
