package com.example.sopcode.authorization;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.sopcode.R;

public class PermissionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        init();
    }

    private void init() {
        TextView tvStatus = findViewById(R.id.tv_status);
        tvStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
                //requestPermission();
            }
        });
    }

    private final int REQUEST_CODE = 2;//请求码

    /**
     * 请求读取sd卡的权限
     */
    private void requestPermission() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};//只需要在这里添加权限即可；
        for (String permission : permissions) {
            if (permissionUtil.isOwnPermisson(this, permission)) {
                //如果已经拥有改权限
                Log.i("request", "own");
            } else {
                //没有该权限，需要进行请求
                permissionUtil.requestPermission(this, permission, REQUEST_CODE);
            }
        }
    }

    /**
     * 权限申请返回结果
     *
     * @param requestCode  请求码
     * @param permissions  权限数组
     * @param grantResults 申请结果数组，里面都是int类型的数
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("request", "success   "+grantResults[0]);
                } else {
                    Log.i("request", "failed    ");
                }
                return;
            }
        }
    }

    /**
     * 打电话方法
     */
    private void makeCall() {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + "09179062006");
            intent.setData(data);
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}
