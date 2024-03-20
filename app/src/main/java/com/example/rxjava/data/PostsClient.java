package com.example.rxjava.data;

import com.example.rxjava.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsClient {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private final PostInterface postInterface;
    private static PostsClient INSTANCE;

    public PostsClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        postInterface = retrofit.create(PostInterface.class);
    }

    public static PostsClient getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new PostsClient();
        }
        return INSTANCE;
    }

    public Observable<List<PostModel>> getPosts(){
        return postInterface.getPosts();
    }
}
