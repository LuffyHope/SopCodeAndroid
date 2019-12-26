package com.example.sopcode.testbuttcolor;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sopcode.R;
import com.jakewharton.rxbinding3.widget.RxTextView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function6;
import rx.functions.Action1;
import rx.functions.Func2;

public class TestActivityButton extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_test_bt);
        init();
    }

    private void init() {
        final EditText name = findViewById(R.id.et_name);
        final EditText password = findViewById(R.id.et_password);
        final Button next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivityButton.this, "点击了", Toast.LENGTH_SHORT).show();
            }
        });
        WorksSizeCheckUtil.textChangeListener textChangeListener = new WorksSizeCheckUtil.textChangeListener(next);
        textChangeListener.addAllEditText(name, password);
        WorksSizeCheckUtil.setChangeListener(new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    next.setBackgroundColor(Color.RED);
                    //next.setBackground(ContextCompat.getDrawable(TestActivityButton.this, R.drawable.currency_coin_selected_border_bg));
                } else {
                    next.setBackgroundColor(Color.BLACK);
                    //next.setBackground(ContextCompat.getDrawable(TestActivityButton.this, R.drawable.currency_coin_selected_border_bg));
                }
            }
        });
    }

}
