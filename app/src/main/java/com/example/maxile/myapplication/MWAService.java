package com.example.maxile.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MWAService {
    @GET("eServiceNews.php")
    Call<NewsModel> listNews();
}
