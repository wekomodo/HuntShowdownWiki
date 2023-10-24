package com.wekomodo.huntshowdownwiki.data.remote

import com.wekomodo.huntshowdownwiki.data.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface InterfaceAPI {


    @GET(".")
    suspend fun getLatestNews(
        @Query("appid") appId : String,
        @Query("count") count : String,
        @Query("maxlength") maxLength: String,
        @Query("format")  format : String,
    ): Response<News>
}