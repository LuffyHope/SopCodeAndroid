package com.example.sopcode.testdownfresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sopcode.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DownFreshActivity extends AppCompatActivity {

    private CommonAdapter<String> adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        init();
    }

    private List<String> mList = new ArrayList<>();

    private int time = 1;

    private void init() {
        LinearLayoutManager manager = new LinearLayoutManager(this);

        TextView tvTest = findViewById(R.id.tv_test_adpter);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnScrollListener(monScrollListener);
        tvTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });
    }

    private void requestData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(time + " -- " + i);
        }
        if (null == adapter) {
            setView(list);
        } else {
            mList.addAll(list);
            adapter.notifyDataSetChanged();
        }
        time++;
    }

    private void setView(List<String> list) {
        mList.addAll(list);
        adapter = new CommonAdapter<String>(this, R.layout.item_home_game, mList) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

            }
        };
        recyclerView.setAdapter(adapter);
    }

    private int mLastVisibleItemPosition;
    private RecyclerView.OnScrollListener monScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }
            if (adapter != null) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastVisibleItemPosition + 1 == adapter.getItemCount()) {
                    //发送网络请求获取更多数据
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            requestData();
                        }
                    }, 1000);
                }
            }
        }
    };
}
