package com.example.rxjava;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rxjava.data.PostsClient;
import com.example.rxjava.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PostViewModel extends ViewModel {
    MutableLiveData<List<PostModel>> postsMutableLiveData = new MutableLiveData<>();

    @SuppressLint("CheckResult")
    public void getPosts(){
        Observable<List<PostModel>> observable = PostsClient.getINSTANCE().getPosts();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PostModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(List<PostModel> postModels) {
                        Log.d("rxjava2", "onNext: "+postModels.get(0).getBody());
                        postsMutableLiveData.setValue(postModels);
                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }
}
