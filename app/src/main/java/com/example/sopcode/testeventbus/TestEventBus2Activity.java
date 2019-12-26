package com.example.sopcode.testeventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.sopcode.R;
import com.example.sopcode.utils.ViewUtil;

import org.simple.eventbus.EventBus;

import static com.example.sopcode.testeventbus.EventBusCons.TEST_EVENT_BUS;

public class TestEventBus2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        init();
    }

    private void init() {
        TextView title = findViewById(R.id.title);
        final TestEventBus2Fragment testEventBusFragment = new TestEventBus2Fragment();
        ViewUtil.replaceFragment(this, testEventBusFragment, false, R.id.frame_layout);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post("这是来自2Activity EVentBUs", TEST_EVENT_BUS);
            }
        });
    }
}
