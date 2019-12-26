package com.example.sopcode.testeventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.sopcode.R;
import com.example.sopcode.utils.ViewUtil;

import org.simple.eventbus.EventBus;

public class TestEventBusActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        EventBus.getDefault().register(this);
        init();
    }

    private void init() {
        TextView title = findViewById(R.id.title);
        final TestEventBusFragment testEventBusFragment = new TestEventBusFragment();
        ViewUtil.replaceFragment(this, testEventBusFragment, false, R.id.frame_layout);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //testEventBusFragment.setTitle("activity 设置的值");
                Intent intent = new Intent(TestEventBusActivity.this, TestEventBus2Activity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
