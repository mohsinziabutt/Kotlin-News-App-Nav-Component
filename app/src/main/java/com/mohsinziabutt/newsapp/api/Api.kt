package com.mohsinziabutt.newsapp.api

import com.mohsinziabutt.newsapp.responses.DefaultResponse
import com.mohsinziabutt.newsapp.responses.LoginResponse
import com.mohsinziabutt.newsapp.responses.NewsResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api
{
    @FormUrlEncoded
    @POST("register.php")
    fun createUser(
        @Field("email") email:String,
        @Field("name") name:String,
        @Field("password") password:String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("login.php")
    fun userLogin(
        @Field("email") email:String,
        @Field("password") password: String
    ):Call<LoginResponse>

    @GET("top-headlines?country=us&category=business&apiKey=f540f76d89334a86ad2c80b3a501982d")
    fun getAllHeadlineNews(
    ):Call<NewsResponse>

    @GET("everything?domains=wsj.com&apiKey=f540f76d89334a86ad2c80b3a501982d")
    fun getCommonNews(
    ):Call<NewsResponse>

    @GET("top-headlines?sources=techcrunch&apiKey=f540f76d89334a86ad2c80b3a501982d")
    fun getNavBarNews(
    ):Call<NewsResponse>
}