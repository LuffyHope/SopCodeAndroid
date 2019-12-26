package com.example.sopcode.testeventbus;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sopcode.R;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import static com.example.sopcode.testeventbus.EventBusCons.TEST_EVENT_BUS;

public class TestEventBusFragment extends Fragment {

    private TextView mTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_bus, container, false);
        init(view);
        return view;

    }

    private void init(View view) {
        mTitle = view.findViewById(R.id.title);
    }

    @Subscriber(tag = TEST_EVENT_BUS)
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
