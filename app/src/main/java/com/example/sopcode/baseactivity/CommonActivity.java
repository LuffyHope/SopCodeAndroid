package com.example.sopcode.baseactivity;

import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.sopcode.R;
import com.example.sopcode.utils.ViewUtil;

/**
 * author: Rea.X
 * date: 2017/5/19.
 */

public abstract class CommonActivity<T extends ViewDataBinding> extends UIActivity<T> {
    protected View titleBarView;
    protected Toolbar toolbar;
    protected TextView tvTitle;

    private boolean showMenu = true;

    @Override
    protected void brforeInit() {
        super.brforeInit();
        initToolbar();
    }

    @Override
    protected void init() {

    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar == null) return;
        titleBarView = findViewById(R.id.title_bar_view);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle("");
        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText(setTitleText() == null ? "" : setTitleText());
        if (setToolbarBackgroundColor() != 0) {
            toolbar.setBackgroundColor(setToolbarBackgroundColor());
        }
        if (setToolbarBackgroundRes() != 0) {
            toolbar.setBackgroundResource(setToolbarBackgroundRes());
        }
        if (titleBarView != null) {
            if (setStatusViewBackgroundColor() != 0) {
                titleBarView.setBackgroundColor(setStatusViewBackgroundColor());
            }
            if (setStatusViewBackgroundRes() != 0) {
                titleBarView.setBackgroundResource(setStatusViewBackgroundRes());
            }
            ViewUtil.immersionBar(immersionBar, titleBarView, false);
        }
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPress();
            }
        });
        ViewUtil.immersionBar(immersionBar, titleBarView);
    }

    protected void onBackPress() {
        finish();
    }

    protected abstract String setTitleText();

    protected @DrawableRes
    int setToolbarBackgroundRes() {
        return 0;
    }

    protected @ColorInt
    int setToolbarBackgroundColor() {
        return Color.parseColor("#ffffff");
    }

    protected @DrawableRes
    int setStatusViewBackgroundRes() {
        return 0;
    }

    protected @ColorInt
    int setStatusViewBackgroundColor() {
        return Color.parseColor("#ffffff");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (showMenu) {
            getMenuInflater().inflate(R.menu.menu_kefu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_service) {
            //公共的的头部功能。
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }
}
