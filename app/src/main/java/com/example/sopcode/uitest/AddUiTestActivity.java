package com.example.sopcode.uitest;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.sopcode.R;
import com.example.sopcode.utils.ScreenUtil;

public class AddUiTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_layout);
        init();
    }

    private void init() {
        final LinearLayout linear_add = findViewById(R.id.linear_add);
        final LinearLayout linear_test = findViewById(R.id.linear_test);
        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWidthAndHeight(linear_add);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_add.removeAllViews();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_add.destroyDrawingCache();

                copyText("");
            }
        });
    }
    private void copyText(String text){
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", text);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
    }

    private void setWidthAndHeight(LinearLayout linear_add) {
        View view = getLayoutInflater().inflate(R.layout.item_add, null);
        int imageWidth = ScreenUtil.dp2px(this, 150);
        int imageHeight = ScreenUtil.dp2px(this, 200);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(imageWidth, imageHeight);
        layoutParams.setMargins(10, 10, 10, 10);
        view.setLayoutParams(layoutParams);
        linear_add.addView(view);
    }
}
