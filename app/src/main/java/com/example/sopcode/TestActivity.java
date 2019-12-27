package com.example.sopcode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sopcode.authorization.PermissionActivity;
import com.example.sopcode.baseactivity.test.TestCommonActivity;
import com.example.sopcode.basebottomdialog.TestBottomDialog;
import com.example.sopcode.basedialog.DialogTest;
import com.example.sopcode.baserecycleview.RecycleViewActivity;
import com.example.sopcode.lunbo.LunboActivity;
import com.example.sopcode.popwindow.PopupWindow1;
import com.example.sopcode.recyclerheadtest.RecyclerDifferentItemActivity;
import com.example.sopcode.recyclerheadtest.RecyclerTestActivity;
import com.example.sopcode.studyui.CustomizeActivity;
import com.example.sopcode.system_promise_summary.activity.SystemSummaryActivity;
import com.example.sopcode.tesanimation.ActivityTestAnimation;
import com.example.sopcode.testbuttcolor.TestActivityButton;
import com.example.sopcode.testdownfresh.DownFreshActivity;
import com.example.sopcode.testeventbus.TestEventBusActivity;
import com.example.sopcode.testretrofit.TestRetrofitActivity;
import com.example.sopcode.testrxjava.RxjavaActivity;
import com.example.sopcode.testscreenchange.TestScreenActivity;
import com.example.sopcode.uitest.AddUiTestActivity;
import com.example.sopcode.uitest.UITestActivity;
import com.example.sopcode.utils.EventHelper;
import com.example.sopcode.utils.activity.TestUtilsActivity;
import com.facebook.common.logging.LoggingDelegate;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "==-==--==";
    private ImageView imageView;
    private ImageView circle_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Log.d(TAG, "屏幕  密度：onCreate: " + Resources.getSystem().getDisplayMetrics().density);

        String json = "{\"data\":{\"key\":\"value\"},\"msg\":\"取款金额不足以抵扣手续费,请重新输入！\",\"status\":\"888888\"}";
        JSONObject jsons = null;
        try {
            jsons = new JSONObject(json);
            String data = jsons.getString("data");
            JSONObject dung = new JSONObject(data);
            dung.put("dung", true);
            Log.d(TAG, "onCreate: " + dung.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        TextView test = findViewById(R.id.tv_test);
        TextView tv_intent = findViewById(R.id.tv_intent);
        TextView tv_dialog = findViewById(R.id.tv_dialog);
        TextView tv_recycle = findViewById(R.id.tv_recycle);
        TextView tv_test_ui = findViewById(R.id.tv_test_ui);
        TextView tv_add_linear = findViewById(R.id.tv_add_linear);
        TextView tv_test_rxjava = findViewById(R.id.tv_test_rxjava);
        TextView tv_test_rxzip = findViewById(R.id.tv_test_rxzip);
        TextView tv_test_rxfilter = findViewById(R.id.tv_test_rxfilter);
        TextView tv_delete_repyte = findViewById(R.id.tv_delete_repyte);
        TextView tv_okgo_down_bitmap = findViewById(R.id.tv_okgo_down_bitmap);
        TextView tv_test_hy_recycler = findViewById(R.id.tv_test_hy_recycler);
        TextView tv_test_retrofit = findViewById(R.id.tv_test_retrofit);
        TextView tv_test_eventbus = findViewById(R.id.tv_test_eventbus);
        TextView tv_test_screen_change = findViewById(R.id.tv_test_screen_change);
        TextView tv_add_permission = findViewById(R.id.tv_add_permission);
        TextView tv_test_layout_animation = findViewById(R.id.tv_test_layout_animation);
        TextView tv_customize = findViewById(R.id.tv_customize);
        TextView tv_dwon_fresh = findViewById(R.id.tv_dwon_fresh);
        TextView tv_change_butt = findViewById(R.id.tv_change_butt);
        TextView tv_text_recycler_item = findViewById(R.id.tv_text_recycler_item);
        TextView tv_text_view_group = findViewById(R.id.tv_text_view_group);
        TextView tv_text_repeat = findViewById(R.id.tv_text_repeat);
        TextView tv_text_lunbo = findViewById(R.id.tv_text_lunbo);
        TextView tv_to_system = findViewById(R.id.tv_to_system);
        TextView tv_to_utils = findViewById(R.id.tv_to_utils);
        circle_button = findViewById(R.id.circle_button);
        EventHelper.click(this, test, tv_intent, tv_dialog, tv_recycle, tv_test_ui, tv_add_linear,
                tv_test_rxjava, tv_test_rxzip, tv_test_rxfilter, tv_delete_repyte, tv_okgo_down_bitmap
                , tv_test_hy_recycler, tv_test_retrofit, tv_test_eventbus, tv_test_screen_change,
                tv_add_permission, tv_test_layout_animation, tv_customize, tv_dwon_fresh, circle_button,
                tv_change_butt, tv_text_recycler_item, tv_text_view_group, tv_text_repeat, tv_text_lunbo,
                tv_to_system, tv_to_utils);

        imageView = findViewById(R.id.image);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_test:

                List<String> list = new ArrayList<>();
                list.add("d");
                list.add("d");
                list.add("d");
                list.add("d");
                list.add("d");
                list.add("d");
                Log.d(TAG, "onClick: " + list.size());
                TestBottomDialog bottom = TestBottomDialog.newInstance();
                bottom.show(getSupportFragmentManager(), "ddf");
                break;
            case R.id.tv_intent:
                Intent intent = new Intent(TestActivity.this, TestCommonActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_dialog:
                DialogTest dialogTest = DialogTest.newInstance();
                dialogTest.show(getSupportFragmentManager());
                break;
            case R.id.tv_recycle:
                intentToActivity(RecycleViewActivity.class);
                break;
            case R.id.tv_test_ui:
                intentToActivity(UITestActivity.class);
                break;
            case R.id.tv_add_linear:
                intentToActivity(AddUiTestActivity.class);
                break;
            case R.id.tv_test_rxjava:
                testRxjava();
                break;
            case R.id.tv_test_rxzip:
                testRxZip();
                break;
            case R.id.tv_test_rxfilter:
                testRxFilter();
                break;
            case R.id.tv_delete_repyte:
                testRemoveRepete();
                break;
            case R.id.tv_okgo_down_bitmap:
                requestBitmap();
                break;
            case R.id.tv_test_hy_recycler:
                intentToActivity(RecyclerTestActivity.class);
                break;
            case R.id.tv_test_retrofit:
                testRetrofit();
                break;
            case R.id.tv_test_eventbus:
                testEventBUS();
                break;
            case R.id.tv_test_screen_change:
                testScreenChange();
                break;
            case R.id.tv_add_permission:
                intentToActivity(PermissionActivity.class);
                break;
            case R.id.tv_test_layout_animation:
                intentToActivity(ActivityTestAnimation.class);
                break;
            case R.id.tv_customize:
                intentToActivity(CustomizeActivity.class);
                break;
            case R.id.tv_dwon_fresh:
                intentToActivity(DownFreshActivity.class);
                break;
            case R.id.tv_change_butt:
                intentToActivity(TestActivityButton.class);
                break;
            case R.id.circle_button:
                testSuspension();
                break;
            case R.id.tv_text_recycler_item:
                intentToActivity(RecyclerDifferentItemActivity.class);
                break;
            case R.id.tv_text_view_group:
                intentToActivity(AddUiTestActivity.class);
                break;
            case R.id.tv_text_repeat:
                intentToActivity(RxjavaActivity.class);
                break;
            case R.id.tv_text_lunbo:
                intentToActivity(LunboActivity.class);
                break;
            case R.id.tv_to_system:
                intentToActivity(SystemSummaryActivity.class);
                break;
            case R.id.tv_to_utils:
                intentToActivity(TestUtilsActivity.class);
                break;
            default:
                break;
        }
    }


    private void testSuspension() {
        Intent intent = new Intent(this, PopupWindow1.class);
        startActivity(intent);
    }

    private void testScreenChange() {
        Intent intent = new Intent(this, TestScreenActivity.class);
        startActivity(intent);
    }

    private void testEventBUS() {
        Intent intent = new Intent(this, TestEventBusActivity.class);
        startActivity(intent);
    }

    private void testRetrofit() {
        Intent intent = new Intent(this, TestRetrofitActivity.class);
        startActivity(intent);
    }

    private void requestBitmap() {
        OkGo.<Bitmap>post("http://www.ghost64.com/qqtupian/zixunImg/local/2018/11/02/15411534243501.jpeg")
                .tag(this)
                // .headers()
                .execute(new BitmapCallback() {
                    @Override
                    public void onSuccess(Response<Bitmap> response) {
                        Bitmap body = response.body();
                        imageView.setImageBitmap(body);
                    }
                });
    }

    private void testRemoveRepete() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(1);
        arrayList.add(3);
        arrayList.add(2);
        arrayList.add(3);
        arrayList = new ArrayList<>(new HashSet<>(arrayList));
        for (int i = 0; i < arrayList.size(); i++) {
            Log.d(TAG, "testRemoveRepete: i " + i);
        }
    }

    @SuppressLint("CheckResult")
    private void testRxFilter() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
            }
        }).subscribeOn(Schedulers.io())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        Log.d(TAG, "test: " + Thread.currentThread().getName());
                        return integer > 2;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "accept: " + Thread.currentThread().getName());
                        Log.d(TAG, "accept: integer " + integer);
                    }
                });
    }

    private void testRxZip() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Log.d(TAG, "emit 3");
                emitter.onNext(3);
                Log.d(TAG, "emit 4");
                emitter.onNext(4);
                Log.d(TAG, "emit complete1");
                emitter.onComplete();
            }
        });

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "emit A");
                emitter.onNext("A");
                Log.d(TAG, "emit B");
                emitter.onNext("B");
                Log.d(TAG, "emit C");
                emitter.onNext("C");
                Log.d(TAG, "emit complete2");
                emitter.onComplete();
            }
        });

        Disposable disposable = Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: " + s);
            }
        });
    }

    private void testRxjava() {
        Observable.just("")
                .subscribeOn(Schedulers.io())//这个方法指执行一次；
                //map的功能是将Function中的第一个参数转为第二个参数返回 传个下一级的Observable
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        Log.d("==--==--==", "1 :  " + Thread.currentThread().getName());
                        return "";
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//每执行一次就会切换一次线程；
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        Log.d("==--==--==", "3 :  " + Thread.currentThread().getName());
                        ((TextView) findViewById(R.id.tv_test_rxjava)).setText(s);
                        return "";
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        Log.d("==--==--==", "4 :  " + Thread.currentThread().getName());
                        return "";
                    }
                })
                .subscribe();
    }

    private void intentToActivity(Class claz) {
        Intent intent = new Intent(TestActivity.this, claz);
        startActivity(intent);
    }
}
