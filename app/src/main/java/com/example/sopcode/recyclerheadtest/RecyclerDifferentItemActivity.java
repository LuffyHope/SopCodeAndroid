package com.example.sopcode.recyclerheadtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.sopcode.R;
import com.example.sopcode.utils.EventHelper;

import java.util.ArrayList;
import java.util.List;

public class RecyclerDifferentItemActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private List<String> list;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        EventHelper.click(this, buttonDisplay, buttonReqeat);
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("第几项 + " + i);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_display:
                ActivityCommonAdapter adapter = new ActivityCommonAdapter(list);
                recyclerView.setAdapter(adapter);
                break;
            case R.id.button_reqeat:

                break;
            default:
                break;
        }
    }
}
