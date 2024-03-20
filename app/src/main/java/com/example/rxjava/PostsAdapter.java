package com.example.rxjava;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjava.pojo.PostModel;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {

    private List<PostModel> moviesList = new ArrayList<>();
    @NonNull
    @Override
    public PostsAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.PostViewHolder holder, int position) {
        holder.title.setText(moviesList.get(position).getTitle());
        holder.userId.setText(moviesList.get(position).getUserId());
        holder.post.setText(moviesList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMoviesList(List<PostModel> moviesList){
        this.moviesList = moviesList;
        notifyDataSetChanged();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder{

        TextView title, userId, post;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_tv);
            userId = itemView.findViewById(R.id.userId_tv);
            post = itemView.findViewById(R.id.body_tv);
        }
    }
}
