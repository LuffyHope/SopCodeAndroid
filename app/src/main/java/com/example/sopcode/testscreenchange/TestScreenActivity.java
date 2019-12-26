package com.example.sopcode.testscreenchange;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sopcode.R;
import com.example.sopcode.utils.EventHelper;

public class TestScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_change);//切换屏幕自主切换
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        init();
    }

    private void init() {
        Button horizontal = findViewById(R.id.button1);
        Button vertical = findViewById(R.id.button2);
        tvContent = findViewById(R.id.tv_content);
        EventHelper.click(this, horizontal, vertical);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                onClickHori();
                break;
            case R.id.button1:
                onClickVertical();
                break;
            default:
                break;
        }
    }

    private void onClickVertical() {
        changeScreen();
    }

    private void onClickHori() {
        changeScreen();
    }

    //改变横竖屏切换的方法  //手动切换  与上面的监听传感器 只能二选一
    public void changeScreen() {
        /**
         * int ORIENTATION_PORTRAIT = 1;  竖屏
         * int ORIENTATION_LANDSCAPE = 2; 横屏
         */
        //获取屏幕的方向  ,数值1表示竖屏，数值2表示横屏
        int screenNum = getResources().getConfiguration().orientation;
        //（如果竖屏）设置屏幕为横屏
        if (screenNum == 1) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            //设置为置屏幕为竖屏
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    //屏幕方向发生改变的回调方法
    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            tvContent.append("\n 当前屏幕为横屏");
        } else {
            tvContent.append("\n 当前屏幕为竖屏");
        }
        super.onConfigurationChanged(newConfig);
        Log.e("TAG", "onConfigurationChanged");
        //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);  //设置横屏
    }
}
