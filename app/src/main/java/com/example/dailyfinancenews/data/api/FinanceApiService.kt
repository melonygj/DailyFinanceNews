package com.example.dailyfinancenews.data.api

import com.example.dailyfinancenews.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FinanceApiService {
    
    // 获取每日热点新闻
    @GET("api/v1/news/daily")
    suspend fun getDailyNews(
        @Query("date") date: String? = null,
        @Query("category") category: String? = null
    ): Response<NewsResponse>
    
    // 获取快讯
    @GET("api/v1/news/flash")
    suspend fun getFlashNews(
        @Query("limit") limit: Int = 50
    ): Response<FlashNewsResponse>
    
    // 获取专题列表
    @GET("api/v1/topics")
    suspend fun getTopics(): Response<TopicsResponse>
    
    // 获取专题详情
    @GET("api/v1/topics/{id}")
    suspend fun getTopicDetail(@Path("id") topicId: String): Response<Topic>
    
    // 获取晚报
    @GET("api/v1/reports/evening")
    suspend fun getEveningReport(
        @Query("date") date: String? = null
    ): Response<EveningReport>
    
    // 搜索新闻
    @GET("api/v1/news/search")
    suspend fun searchNews(
        @Query("keyword") keyword: String,
        @Query("category") category: String? = null
    ): Response<NewsResponse>
    
    companion object {
        const val BASE_URL = "https://api.example.com/"
    }
}