package com.example.rxjava;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjava.pojo.PostModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {

    private List<PostModel> moviesList = new ArrayList<>();
    LayoutInflater layoutInflater;
    public PostsAdapter(Context context){
       this.layoutInflater  = LayoutInflater.from(context);
    }
    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public PostsAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(layoutInflater.inflate(R.layout.post_item,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.PostViewHolder holder, int position) {
        Log.d("rxjava2", "onBind: "+moviesList.get(position).getBody());
        holder.title.setText(moviesList.get(position).getTitle());
        holder.userId.setText(""+moviesList.get(position).getUserId());
        holder.post.setText(moviesList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMoviesList(List<PostModel> moviesList){

        this.moviesList = moviesList;
        notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{
        TextView title, userId, post;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title_tv);
            this.userId = itemView.findViewById(R.id.userId_tv);
            this.post = itemView.findViewById(R.id.body_tv);
        }
    }
}
