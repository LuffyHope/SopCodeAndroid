package com.example.sopcode.studyui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sopcode.R;

public class CustomizeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);
        init();
    }

    private void init() {
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int top = button.getTop();
                int bottom = button.getBottom();
                int left = button.getLeft();
                int right = button.getRight();
                Log.d("===--===--===", "init: top =  " + top + "\n  bottom = " + bottom + "\n left = " + left + "\n right = " + right);
            }
        });
    }
}
