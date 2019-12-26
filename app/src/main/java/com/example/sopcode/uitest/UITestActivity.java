package com.example.sopcode.uitest;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sopcode.R;
import com.example.sopcode.utils.EventHelper;
import com.example.sopcode.utils.JsonParse;
import com.example.sopcode.view.CircleProgress;
import com.example.sopcode.view.CodeUtils;
import com.example.sopcode.view.MyEditText;
import com.example.sopcode.view.pickerview.ChianProviceCity;
import com.example.sopcode.view.pickerview.CustomizePickerPopWin;
import com.example.sopcode.view.pickerview.DatePickerPopWin;
import com.example.sopcode.view.pickerview.PlacePickerPopWin;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 这里包括 ProgressBar EditText焦点的监听
 */
public class UITestActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private ProgressBar progressBar;
    private TextView textView;
    private EditText editText;
    private CircleProgress progressCircle;
    private ImageView imageVerify;
    private EditText etPrint;
    private ImageView imageView;
    private PasswordStrengthView checkPassword;
    private RegistProgressView progressView;
    private MyEditText my_edit_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        init();
    }

    private void init() {
        Button testPrograss = findViewById(R.id.bu_test);
        editText = findViewById(R.id.edit_text);
        textView = findViewById(R.id.tv_test);


        progressBar = findViewById(R.id.progressBar1);
        progressBar.setMax(100);
        progressBar.setProgressDrawable(this.getResources().getDrawable(R.drawable.color_progressbar, null));
        editText.setOnFocusChangeListener(this);
        /**
         *  在EditText的父级控件加上
         *  android:focusable="true"
         *  android:focusableInTouchMode="true"
         *  就可以去掉自动获取焦点的功能；
         */

        //圆形进度条
        progressCircle = findViewById(R.id.progress_circle);

        imageVerify = findViewById(R.id.image_verify);
        bitmap = CodeUtils.getInstance().createBitmap();
        //获取当前图片验证码的对应内容用于校验
        code = CodeUtils.getInstance().getCode();
        imageVerify.setImageBitmap(bitmap);

        //选择控件的东西;
        TextView chooseData = findViewById(R.id.tv_choose_data);
        TextView chooseCity = findViewById(R.id.tv_choose_city);
        TextView chooseSimple = findViewById(R.id.tv_choose_simple);


        etPrint = findViewById(R.id.edit_print);
        imageView = findViewById(R.id.image_eye);
        Button btn_test_strngth = findViewById(R.id.btn_test_strngth);
        Button bt_test_progress = findViewById(R.id.bt_test_progress);
        Button bt_test_progress2 = findViewById(R.id.bt_test_progress2);
        Button bt_test_progress3 = findViewById(R.id.bt_test_progress3);
        Button button_text = findViewById(R.id.button_text);
        my_edit_text = findViewById(R.id.my_edit_text);
        checkPassword = findViewById(R.id.check_password_strength);

        progressView = findViewById(R.id.regist_progress);
        EventHelper.click(this, testPrograss, textView, imageVerify, chooseData, chooseCity,
                chooseSimple, imageView, btn_test_strngth, bt_test_progress, bt_test_progress2,bt_test_progress3,button_text);
    }

    private Bitmap bitmap;
    private String code;

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        //EditText获取焦点时为true 反之为false;
        if (hasFocus) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bu_test:
                testPrograssBar();
                break;
            case R.id.tv_test:
                editText.clearFocus();
                break;
            case R.id.image_verify:
                changeVerifyBitmap();
                break;
            case R.id.tv_choose_data:
                setBirthDay();
                break;
            case R.id.tv_choose_city:
                chooseCity();
                break;
            case R.id.tv_choose_simple:
                chooseSimple();
                break;
            case R.id.image_eye:
                toggleEyePassword();
                break;
            case R.id.btn_test_strngth:
                requestPassWord(status, UITestActivity.this);
                status++;
                break;
            case R.id.bt_test_progress:
                testProgress(0, UITestActivity.this);
                status++;
                break;
            case R.id.bt_test_progress2:
                testProgress(1, UITestActivity.this);
                status--;
                break;
            case R.id.bt_test_progress3:
                testProgress(2, UITestActivity.this);
                break;
            case R.id.button_text:
                String json = "{\"activityTypeList\":[{\"name\":\"全部\"},{\"id\":2,\"name\":\"测试分类\"},{\"id\":5,\"name\":\"新增分类\"}]}";
                JSONObject dung = null;
                try {
                    dung = new JSONObject(json);
                    dung.put("dung", true);
                    Log.d("==--==--==", "onClick: string = "+dung.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            default:
                break;
        }
    }

    private int status = 0;

    private void requestPassWord(int status, Context context) {
        checkPassword.setViewStatus(status, context);
    }

    private void testProgress(int status, Context context) {
        progressView.setViewStatus(status);
    }

    /**
     * 验证码的用法
     */
    private void changeVerifyBitmap() {
        bitmap = CodeUtils.getInstance().createBitmap();
        code = CodeUtils.getInstance().getCode();
        imageVerify.setImageBitmap(bitmap);
    }

    Handler handler = new Handler();

    private void testPrograssBar() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(100);
                        final int finalI = i;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(finalI);
                                progressCircle.setProgress(finalI);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void setBirthDay() {
        DatePickerPopWin birthday = new DatePickerPopWin.Builder(this, new DatePickerPopWin.OnDatePickedListener() {
            @Override
            public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                Toast.makeText(UITestActivity.this, dateDesc, Toast.LENGTH_SHORT).show();
            }
        }).build();
        birthday.showPopWin(this);
    }

    private void chooseSimple() {
        List<String> mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add("第几条数据 " + i);
        }
        CustomizePickerPopWin pickerPopWin = new CustomizePickerPopWin.Builder(this, new CustomizePickerPopWin.IPickedListener() {
            @Override
            public void setDataString(String dataString) {
                Toast.makeText(UITestActivity.this, dataString, Toast.LENGTH_SHORT).show();
            }
        }).setDataList(mList)
                .build();
        pickerPopWin.showPopWin(this);
    }

    private void chooseCity() {
        String json = getJson("area.json");
        List<ChianProviceCity> chinaPlace = JsonParse.fromJson(json, new TypeToken<List<ChianProviceCity>>() {
        }.getType());
        PlacePickerPopWin pickerPopWin = new PlacePickerPopWin.Builder(this, new PlacePickerPopWin.IPickedListener() {
            @Override
            public void setDataString(String provide, String city) {
                Toast.makeText(UITestActivity.this, provide + "  " + city, Toast.LENGTH_SHORT).show();
            }
        }).setProvideAndCity(chinaPlace)
                .build();
        pickerPopWin.showPopWin(this);
    }

    public String getJson(String path) {
        StringBuffer sb = new StringBuffer();
        AssetManager am = getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(am.open(path)));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString().trim();
    }

    private void toggleEyePassword() {
        imageView.setSelected(!imageView.isSelected());
        if (imageView.isSelected()) {
            etPrint.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            etPrint.setSelection(etPrint.getText().length());
        } else {
            etPrint.setTransformationMethod(PasswordTransformationMethod.getInstance());
            etPrint.setSelection(etPrint.getText().length());
        }
    }
}
