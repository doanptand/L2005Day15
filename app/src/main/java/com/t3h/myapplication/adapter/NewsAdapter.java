package com.t3h.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.t3h.myapplication.R;
import com.t3h.myapplication.callback.OnNewsItemClickListener;
import com.t3h.myapplication.model.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<News> arrNews;
    private Context context;
    private OnNewsItemClickListener callback;

    public NewsAdapter(List<News> arrNews, Context context, OnNewsItemClickListener callback) {
        this.arrNews = arrNews;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        News news = arrNews.get(position);
        holder.tvTitle.setText(news.getTitle());
        holder.tvDate.setText(news.getPubDate());
        holder.tvDescription.setText(news.getDescription());
        Glide.with(context).load(news.getAvatar())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imgAvatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onNewsItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrNews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView tvTitle, tvDescription, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.img_doanpt);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvDate = itemView.findViewById(R.id.tv_date);
        }
    }
}
