package com.wsloan.rxbus;


import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * 使用方法
 */

public class RxBus {
    private static volatile RxBus defaultInstance;
    private Subscription subscribe;
    private final Subject<Object, Object> bus;
    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    public RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }
    // 单例RxBus
    public static RxBus getDefault() {
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance ;
    }
    // 发送一个新的事件
    public void post (Object o) {
        bus.onNext(o);
    }


    public <T> Subscription subscribe (Class<T> eventType, Action1<T> callback, Action1<Throwable> actionError){
        if (actionError != null){
            return subscribe = bus.ofType(eventType).subscribe(callback,actionError);
        } else {
            return subscribe = bus.ofType(eventType).subscribe(callback);
        }
    }

    public <T> Subscription subscribe (Class<T> eventType, Action1<T> callback){
        return subscribe = subscribe(eventType,callback,null);
    }

    public void unRegister(){
        if(!subscribe.isUnsubscribed()) {
            subscribe.unsubscribe();
        }
    }
}
