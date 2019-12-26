package com.example.sopcode.tesanimation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sopcode.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ActivityTestAnimation extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation);
        init();
    }

    private void init() {
        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutAnimation(animation);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("数据第几个 ： " + i);
        }
        final GridLayoutManager layoutManage = new GridLayoutManager(this, 5);
        layoutManage.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManage);
        final CommonAdapter commonAdapter = new CommonAdapter<String>(this, R.layout.item_home_game, list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv_title, s);
            }
        };

        recyclerView.setAdapter(commonAdapter);
        final LinearLayout linear = findViewById(R.id.linear);
        final ImageView im = findViewById(R.id.image);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runLayoutAnimation(recyclerView);
            }
        });
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}
