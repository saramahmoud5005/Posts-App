package com.example.rxjava.data;

import com.example.rxjava.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PostInterface {
    @GET("posts")
    Observable<List<PostModel>> getPosts();
}
