package com.t3h.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.t3h.myapplication.adapter.NewsAdapter;
import com.t3h.myapplication.callback.OnNewsItemClickListener;
import com.t3h.myapplication.model.News;
import com.t3h.myapplication.parser.NewsParser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
implements OnNewsItemClickListener {
    private RecyclerView rvNews;
    private List<News> arrNews;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrNews = new ArrayList<>();
        adapter = new NewsAdapter(arrNews, this, this);
        rvNews = findViewById(R.id.rv_news);
        rvNews.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        rvNews.setAdapter(adapter);
        new FetchNewsTask().execute("https://cdn.24h.com.vn/upload/rss/trangchu24h.rss");
    }

    @Override
    public void onNewsItemClick(int position) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(arrNews.get(position).getDetailLink()));
        startActivity(intent);
    }

    class FetchNewsTask extends AsyncTask<String, Void, List<News>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Start fetching",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        protected List<News> doInBackground(String... args) {
            String link = args[0];
            NewsParser parser = new NewsParser();
            return parser.parseNews(link);
        }

        @Override
        protected void onPostExecute(List<News> news) {
            super.onPostExecute(news);
            arrNews.clear();
            arrNews.addAll(news);
            adapter.notifyDataSetChanged();
        }
    }
}