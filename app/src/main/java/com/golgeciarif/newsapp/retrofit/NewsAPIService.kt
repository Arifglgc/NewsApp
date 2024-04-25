package com.golgeciarif.newsapp.retrofit

import com.golgeciarif.newsapp.newsPojo.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {
    @GET("top-headlines?country=us")
    suspend fun getLatestNews(): Response<News>

    @GET("top-headlines?country=us")
    suspend fun getLatestNewsByCategory(
        @Query("category") categoryName: String
    ): Response<News>


}