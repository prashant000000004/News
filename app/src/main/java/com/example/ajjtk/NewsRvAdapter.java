package com.example.ajjtk;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsRvAdapter extends RecyclerView.Adapter<NewsRvAdapter.ViewHolder> {
    private ArrayList<Article> articelsArrayList;
    private Context context;

    public NewsRvAdapter(ArrayList<Article> articelsArrayList, Context context) {
        this.articelsArrayList = articelsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rv_item,parent,false);
      return new NewsRvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRvAdapter.ViewHolder holder, int position) {
        Article articles=articelsArrayList.get(position);
        holder.subTitleTv.setText(articles.getDescription());
        holder.titleTv.setText(articles.getTitle());
        Picasso.get().load(articles.getUrlToImage()).into(holder.newsIv);
        holder.itemView.setOnClickListener(view -> {
            Intent intent=new Intent(context,NewsDetail.class);
            intent.putExtra("title",articles.getTitle());
            intent.putExtra("content",articles.getContent());
            intent.putExtra("desc",articles.getDescription());
            intent.putExtra("image",articles.getUrlToImage());
            intent.putExtra("url",articles.getUrl());
        });
    }

    @Override
    public int getItemCount() {
        return articelsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTv,subTitleTv;
        private ImageView newsIv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv=itemView.findViewById(R.id.idTvNewsHeading);
            subTitleTv=itemView.findViewById(R.id.idTvSubHeading);
            newsIv=itemView.findViewById(R.id.idIvNews);
        }
    }
}
