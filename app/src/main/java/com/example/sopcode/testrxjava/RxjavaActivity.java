package com.example.sopcode.testrxjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sopcode.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

public class RxjavaActivity extends AppCompatActivity {
    private static final String TAG = RxjavaActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        init();
    }

    private void init() {
        Button btRequest = findViewById(R.id.bt_request);
        btRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });
    }

    private void request() {
        /*Observable.just(1, 2, 3, 1, 2)
                .distinct()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "不重复的整型事件元素是： " + integer);
                    }
                });

        // 使用2：过滤事件序列中 连续重复的事件
        // 下面序列中，连续重复的事件 = 3、4
        Observable.just(1, 2, 3, 1, 2, 3, 3, 4, 4)
                .distinctUntilChanged()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "不连续重复的整型事件元素是： " + integer);
                    }
                });*/
        RxBus.getInstance().toObservable(Bean.class).lift(
                new DistinctWithTimeout<>(3, TimeUnit.SECONDS, new Func1<Object, Object>() {
                    @Override
                    public Object call(Object o) {
                        Log.d(TAG, "call: enter");
                        return null;
                    }
                }, AndroidSchedulers.mainThread(), new Action1() {
                    @Override
                    public void call(Object o) {
                        Log.d(TAG, "call: 2");
                    }
                })
        ).subscribe();


        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("2");
                subscriber.onCompleted();
            }
        });

        Subscriber subscriber = new Subscriber() {
            @Override public void onCompleted() {
                Log.d(TAG, "onCompleted: enter");
            }

            @Override public void onError(Throwable e) {
                Log.d(TAG, "onError: enter");
            }

            @Override public void onNext(Object o) {
                Log.d(TAG, "onNext: ");
            }
        };

        observable.lift(new Observable.Operator<Integer, String>() {
            @Override
            public Subscriber<? super String> call(final Subscriber<? super Integer> subscriber) {
                return new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: enter 2");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: enter 2");
                    }

                    @Override
                    public void onNext(String s) {
                        subscriber.onNext(Integer.decode(s));
                        Log.d(TAG, "onNext: enter 2 = "+s);
                    }
                };
            }

        }).subscribe(subscriber);
    }

}
