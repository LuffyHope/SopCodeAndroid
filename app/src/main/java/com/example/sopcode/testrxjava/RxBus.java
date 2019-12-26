package com.example.sopcode.testrxjava;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class RxBus {
    private final Subject<Object, Object> bus;
    private static RxBus defaultRxBus;

    private RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getInstance() {
        if (null == defaultRxBus) {
            synchronized (RxBus.class) {
                if (null == defaultRxBus) {
                    defaultRxBus = new RxBus();
                }
            }
        }
        return defaultRxBus;
    }

    /*
     * 发送
     */
    public void post(Object o) {
        bus.onNext(o);
    }

    /*
     * 是否有Observable订阅
     */
    public boolean hasObservable() {
        return bus.hasObservers();
    }

    /*
     * 转换为特定类型的Obserbale
     */
    public <T> Observable<T> toObservable(Class<T> type) {
        return bus.ofType(type);
    }
}
