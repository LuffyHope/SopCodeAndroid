package com.example.sopcode.system_promise_summary.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.sopcode.R;
import com.example.sopcode.system_promise_summary.utils.NotificationsUtils;
import com.example.sopcode.utils.EventHelper;
import com.example.sopcode.utils.ToastUtils;

import org.w3c.dom.Text;

public class SystemSummaryActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_summary);
        init();
    }

    private void init() {
        TextView tv_notice =  findViewById(R.id.tv_notice);
        EventHelper.click(this,tv_notice);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_notice:
                initNotifications();
                break;
            default:
                break;
        }
    }

    private void initNotifications() {
        if (!NotificationsUtils.isNotificationEnabled(this)) {
            NotificationsUtils.openPermissionSetting(this);
        }else {
            ToastUtils.showToast(this,"通知栏权限已经开启");
        }
    }
}
