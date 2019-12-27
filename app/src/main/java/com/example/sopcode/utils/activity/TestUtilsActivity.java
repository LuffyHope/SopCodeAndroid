package com.example.sopcode.utils.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sopcode.R;
import com.example.sopcode.utils.EventHelper;
import com.example.sopcode.utils.QRCodeUtil;

public class TestUtilsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image_test_qrcode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_utils);
        init();
    }

    private void init() {
        image_test_qrcode = findViewById(R.id.image_test_qrcode);
        TextView tv_test_qrcode =  findViewById(R.id.tv_test_qrcode);
        EventHelper.click(this,tv_test_qrcode);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_test_qrcode://测试二维码
                Bitmap bm = QRCodeUtil.createQRImage("https://www.jianshu.com/u/4632034c7851", 500, 500);
                image_test_qrcode.setImageBitmap(bm);
                break;
            default:
                break;
        }
    }
}
