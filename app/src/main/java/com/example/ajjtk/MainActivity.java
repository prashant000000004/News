package com.example.ajjtk;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ajjtk.databinding.ActivityMainBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//0704a68d07af47c6ac3f873f788a5d2b
public class MainActivity extends AppCompatActivity implements CategoryRvAdapter.CategoryClickInterface {
    private ActivityMainBinding binding;
    private ArrayList<Article> articleArrayList;
    private ArrayList<CategoryRvModel> categoryRvModelsarraylist;
    private CategoryRvAdapter categoryRvAdapter;
    private NewsRvAdapter newsRvAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        articleArrayList=new ArrayList<>();
        categoryRvModelsarraylist=new ArrayList<>();
        newsRvAdapter=new NewsRvAdapter(articleArrayList,this);
        categoryRvAdapter=new CategoryRvAdapter(categoryRvModelsarraylist,this,this::onCategoryClick);
        binding.idRvNews.setLayoutManager(new LinearLayoutManager(this));
        binding.idRvNews.setAdapter(newsRvAdapter);
        binding.idRvCategories.setAdapter(categoryRvAdapter);
        getCategories();
        getNews("All");
        newsRvAdapter.notifyDataSetChanged();

    }
    //getting the data for the categories
    private void getCategories(){
        categoryRvModelsarraylist.add(new CategoryRvModel("All","https://imgs.search.brave.com/Mr9zvKx9t48SHx5ZdlEqYXzE7Lo_NVEvKTOUkOSbGTg/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93d3cu/Z2V0dHlpbWFnZXMu/Y29tL2dpLXJlc291/cmNlcy9pbWFnZXMv/TmV3cy9PY3RvYmVy/MjAyNC9HZXR0eUlt/YWdlcy0yMTc3NTEx/MDA2X0JFU1RORVdT/LmpwZw"));
        categoryRvModelsarraylist.add(new CategoryRvModel("Technology","https://imgs.search.brave.com/uvGa7IclSYxeeYJpJ6BKqtVbOfEGIFxPb4-uf88tQGs/rs:fit:500:0:0:0/g:ce/aHR0cHM6Ly9jZG4u/cGl4YWJheS5jb20v/cGhvdG8vMjAxNi8w/Mi8wMS8wMC81Ni9u/ZXdzLTExNzI0NjNf/NjQwLmpwZw"));
        categoryRvModelsarraylist.add(new CategoryRvModel("Science","https://imgs.search.brave.com/Fb4cwJS93qrclzLr_7debtBcg-eJAk4itbklbTLpDLs/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9pbWFn/ZXMubmF0dXJlLmNv/bS93MTQwaDc5L21h/Z2F6aW5lLWFzc2V0/cy9kNDE1ODYtMDI0/LTAzNTQwLXcvZDQx/NTg2LTAyNC0wMzU0/MC13XzI3NzA5OTQ4/LmpwZw"));
        categoryRvModelsarraylist.add(new CategoryRvModel("Sports","https://imgs.search.brave.com/I9LFmv5Qi5i-ax2-gDu4p2mLIoJQUnUPZbyZDGK_Tsc/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9tZWRp/YS5pc3RvY2twaG90/by5jb20vaWQvOTMy/NzAwMTMvcGhvdG8v/c3BvcnRzLXN1bmRh/eS5qcGc_cz02MTJ4/NjEyJnc9MCZrPTIw/JmM9YnlsQ0IxZFFP/cXV4NDhtV2RzRi1W/ZEtvbm50cUp0S3RD/dWlxSWM2U3Ftcz0"));
        categoryRvModelsarraylist.add(new CategoryRvModel("General","https://imgs.search.brave.com/Rj5X2cvMyUnvEvls-o5vnCU-He2OstV1XFYzmVjRb8I/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9kaW1z/LmFwbmV3cy5jb20v/ZGltczQvZGVmYXVs/dC81ZGMzOWQ1LzIx/NDc0ODM2NDcvc3Ry/aXAvdHJ1ZS9jcm9w/LzUwMjZ4MzM0OCsw/KzIvcmVzaXplLzU5/OXgzOTkhL3F1YWxp/dHkvOTAvP3VybD1o/dHRwczovL2Fzc2V0/cy5hcG5ld3MuY29t/L1stLzE4LywlMjAx/MTUsJTIwLTUsJTIw/NTAsJTIwLTI2LCUy/MC03MiwlMjAtNTEs/JTIwMTAzLCUyMC05/NywlMjAtMjksJTIw/LTQ1LCUyMC0xLCUy/MC03MSwlMjAtNjEs/JTIwLTEwNiwlMjA4/MywlMjAtNzgsJTIw/LTU3LCUyMC0xMCwl/MjAtNTQsJTIwMjcs/JTIwODcsJTIwLTQ4/LCUyMDU0LCUyMDks/JTIwLTEyMSwlMjA2/MSwlMjAxMjFdLzQx/MDE3OWU2ZjU0MTQ2/MjRhYTMyZDNiNjg4/Zjg5MzJm "));
        categoryRvModelsarraylist.add(new CategoryRvModel("Business","https://imgs.search.brave.com/dLrCmADP4TvhrawMNpGXYurT9CPTHATh2EWcimBCieA/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9tZWRp/YS5pc3RvY2twaG90/by5jb20vaWQvMTU3/MTc5ODQ1L3Bob3Rv/L21wMy1leHBhbnNp/b24uanBnP3M9NjEy/eDYxMiZ3PTAmaz0y/MCZjPUtMMnpUd284/MllCNGdXdkNIVE1m/cTdzaHNWNi1Jc1JY/MUMtVEZJUEo0U2s9"));
        categoryRvModelsarraylist.add(new CategoryRvModel("Entertainment","https://imgs.search.brave.com/OJp7cLmvubiGjy0RSShFZ4NfBgAxPS0yjpfzsrvHl4A/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9tZWRp/YS5pc3RvY2twaG90/by5jb20vaWQvMTQw/MDU0NzM2L3Bob3Rv/L21lZGlhLXRlY2hu/b2xvZ2llcy1jb25j/ZXB0LmpwZz9zPTYx/Mng2MTImdz0wJms9/MjAmYz1QWnJrUWtS/d1ZtRDNSeXNZSWZI/Uk9RRlFFaWRyN2s5/RzJNOXJ0UUdheXMw/PQ"));
        categoryRvModelsarraylist.add(new CategoryRvModel("Health","https://imgs.search.brave.com/xautnW_X_ud4Xw4NkZX4ziwCUvT07UYYos3Yb9wXR74/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9tZWRp/YS5jbm4uY29tL2Fw/aS92MS9pbWFnZXMv/c3RlbGxhci9wcm9k/L2dldHR5aW1hZ2Vz/LTEzNDU5NzM2NzQu/anBnP2M9MTZ4OSZx/PWhfNDM4LHdfNzgw/LGNfZmlsbA"));
        categoryRvAdapter.notifyDataSetChanged();
    }

    private void getNews(String category){

        binding.idPbLoading.setVisibility(View.VISIBLE);
        articleArrayList.clear();
        String categoryUrl="https://newsapi.org/v2/top-headlines?country=in&category="+category+"&apiKey=0704a68d07af47c6ac3f873f788a5d2b";
        String url="https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=0704a68d07af47c6ac3f873f788a5d2b";
        String baseUrl="https://newsapi.org/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi retrofitApi=retrofit.create(RetrofitApi.class);
        Call<NewsModel> call;
        if(category.equals("All")){
            call= retrofitApi.getAllNews(url);

        }else {
            call=retrofitApi.getNewsByCategory(categoryUrl);
        }
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel=response.body();
                binding.idPbLoading.setVisibility(View.GONE);
                ArrayList<Article> articles=newsModel.getArticles();
                for(int i=0;i<articles.size();i++){
                    articleArrayList.add(new Article(articles.get(i).getTitle(),
                            articles.get(i).getDescription(),
                            articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent()));
                }

                newsRvAdapter.notifyDataSetChanged();
                
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "fail to get news", Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public void onCategoryClick(int position) {
        String category=categoryRvModelsarraylist.get(position).getCategory();
        getNews(category);

    }
}