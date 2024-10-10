package com.example.blogapp.network

import com.example.blogapp.model.BlogPost
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://blog.vrid.in/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("https://blog.vrid.in/wp-json/wp/v2/posts?per_page=10&page=1")
    suspend fun getBlogs(): List<BlogPost>
}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
