package com.example.maxile.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.security.PublicKey;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    public class RecycleViewModel{
        public RecycleViewModel(String title, String detail, String imgurl){
            this.title = title;
            this.detail = detail;
            this.imgurl = imgurl;
        }
        public String title;
        public String detail;
        public String imgurl;
    }

    public List<RecycleViewModel> datas = new ArrayList<RecycleViewModel>();
    public RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0;i<20;i++){
            datas.add(new RecycleViewModel("What is Lorem Ipsum?", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",""));
        }
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.my_recycle_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter(datas, this);
        recyclerView.setAdapter(this.adapter);

        findViewById(R.id.openform).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LanguageHelper.SwitchLanguage(MainActivity.this);
            }
        });

        loadRequest();
    }

    private void loadRequest(){
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mwa.co.th/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MWAService service = retrofit.create(MWAService.class);
        service.listNews().enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                datas.clear();
                for (NewsItem n :
                        response.body().new_list) {
                    datas.add(new RecycleViewModel(n.title,n.news,n.cover_picture));
                }
                adapter.notifyDataSetChanged();
                ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress);
                progressBar.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {

                ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress);
                progressBar.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });
    }

}
