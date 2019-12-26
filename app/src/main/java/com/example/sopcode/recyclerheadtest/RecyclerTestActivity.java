package com.example.sopcode.recyclerheadtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sopcode.R;
import com.example.sopcode.utils.EventHelper;
import com.example.sopcode.utils.ViewUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

public class RecyclerTestActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private List<String> list;
    private CommonAdapter<String> commonAdapter;
    private TextView tvChange;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_test);
        init();
    }

    private void init() {
        Button buttonDisplay = findViewById(R.id.button_display);
        Button buttonReqeat = findViewById(R.id.button_reqeat);
        recyclerView = findViewById(R.id.recyclerview);
        EventHelper.click(this, buttonDisplay, buttonReqeat);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_display:
                list = new ArrayList<>();
                list.clear();
                for (int i = 0; i < 50; i++) {
                    list.add(String.format("%s %s %s", i, i, i));
                }
                displayRecyclerView(list, "初始化完成");
                break;
            case R.id.button_reqeat:
                changeData();
                break;
            default:
                break;
        }
    }

    private void changeData() {
        if (null == list) {
            list = new ArrayList<>();
        }
        list.clear();
        for (int i = 50; i < 100; i++) {
            list.add(String.format("%s %s %s", i, i, i));
        }
        displayRecyclerView(list, "重置数据");
    }

    private void displayRecyclerView(final List<String> list, String head) {

        final GridLayoutManager layoutManage = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManage);
        commonAdapter = new CommonAdapter<String>(this, R.layout.item_home_game, list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv_title, s);
            }
        };
        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (ViewUtil.isFastClick()) return;
                Toast.makeText(RecyclerTestActivity.this, list.get(position - 1), Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                ImageView imageIcon = holder.itemView.findViewById(R.id.image_icon);
                int status = imageIcon.getVisibility();
                if (status == View.VISIBLE) {
                    imageIcon.setVisibility(View.GONE);
                } else {
                    imageIcon.setVisibility(View.VISIBLE);
                }
                Log.d("status", "status: " + status);
                return true;
            }
        });

        HeaderAndFooterWrapper<Object> headerAndFooterWrapper = new HeaderAndFooterWrapper<>(commonAdapter);
        View view = LayoutInflater.from(this).inflate(R.layout.head_home_recycler, null, false);
        tvChange = view.findViewById(R.id.tv_change);
        tvChange.setText(head);
        headerAndFooterWrapper.addHeaderView(view);
        recyclerView.setAdapter(headerAndFooterWrapper);
    }


}
