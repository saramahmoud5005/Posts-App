package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.example.rxjava.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding =  ActivityMainBinding.inflate(getLayoutInflater());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("TAG10", "onSubscribe: "+"Thread: "+Thread.currentThread().getName());
            }

            @Override
            public void onNext(Object o) {
                Log.d("TAG10", "onNext: "+o.toString()+"Thread: "+Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("TAG10", "onComplete: "+"Thread: "+Thread.currentThread().getName());
            }
        };
        Observable.just("1","2","3")
                .doOnNext(s-> Log.d("TAG10", "onCreate: s"))
                .subscribeOn(Schedulers.io())
                .map((Function<Object, Object>) o -> (o.toString()+"Mapped"))
                .filter(s->s.toString().equals("1"))
                .observeOn(Schedulers.computation())
                .subscribe();


    }
    public void flatMapOperator(){
//        Observable.create(new ObservableOnSubscribe<Object>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
//                        binding.editText.addTextChangedListener(new TextWatcher() {
//                            @Override
//                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                            }
//
//                            @Override
//                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                                emitter.onNext(charSequence);
//                            }
//
//                            @Override
//                            public void afterTextChanged(Editable editable) {
//
//                            }
//                        });
//                    }
//                }).doOnNext(c->Log.d("Main","UpStream"+c))
//                .flatMap(new Function<Object, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(Object o) throws Exception {
//                        return sendDataToApi(o.toString());
//                    }
//                })
//                .subscribe(d->Log.d("Main","downStream"+d));
    }
    public Observable sendDataToApi(String data){
        Observable observable = Observable.just("Api data"+data);
        observable.subscribe(s->Log.d("Main","Api func "+s));
        return observable;

    }
    public void observableFactorMethods(){
        Observable observable = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                emitter.onNext("DONE");
                emitter.onComplete();
            }
        });
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("","on subscribe");
            }

            @Override
            public void onNext(Object o) {
                Log.d("","on next"+o);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("","on complete");
            }
        };
        observable.subscribe(observer);
    }

    public void coldObservable() {
        //cold observable
        Observable<Long> cold = Observable.intervalRange(0, 4, 0, 1, TimeUnit.SECONDS);
        cold.observeOn(Schedulers.io());
        cold.subscribe(i -> Log.d("Cold Observal", "Output1 " + i));
        sleep(3000);
        cold.subscribe(i -> Log.d("Cold Observal", "Output2 " + i));

    }

    public void connectableObservable() {
        //hot observable
        ConnectableObservable<Long> cold = ConnectableObservable.intervalRange(0, 4, 0, 1, TimeUnit.SECONDS).publish();
        cold.connect();
        cold.subscribe(i -> Log.d("Cold Observal", "Output1 " + i));
        sleep(3000);
        cold.subscribe(i -> Log.d("Cold Observal", "Output2 " + i));
    }

    public void hotObservablePublishSubject() {
        //hot observable
        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.subscribe(i -> Log.d("PublishSubject", "Output3 " + i));
        publishSubject.onNext("A");
        sleep(1000);
        publishSubject.subscribe(i -> Log.d("PublishSubject", "Output4 " + i));
        publishSubject.onNext("B");
        sleep(1000);
        publishSubject.onNext("C");
        sleep(1000);
    }

    public void hotObservableReplaySubject() {
        //hot observable
        ReplaySubject<String> replaySubject = ReplaySubject.create();
        replaySubject.subscribe(i -> Log.d("PublishSubject", "Output3 " + i));
        replaySubject.onNext("A");
        sleep(1000);
        replaySubject.subscribe(i -> Log.d("PublishSubject", "Output4 " + i));
        replaySubject.onNext("B");
        sleep(1000);
        replaySubject.onNext("C");
        sleep(1000);
    }

    public void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}