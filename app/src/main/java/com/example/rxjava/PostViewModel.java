package com.example.rxjava;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rxjava.data.PostsClient;
import com.example.rxjava.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PostViewModel extends ViewModel {
    MutableLiveData<List<PostModel>> postsMutableLiveData = new MutableLiveData<>();

    public void getPosts(){
        Observable<List<PostModel>> observable = PostsClient.getINSTANCE().getPosts();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(posts->postsMutableLiveData.setValue(posts));
    }
}
