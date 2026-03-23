package com.example.dailyfinancenews.data.model

import com.google.gson.annotations.SerializedName

// 新闻分类
enum class NewsCategory(val displayName: String, val color: String) {
    MACRO("宏观经济", "#1E88E5"),
    MARKET("资本市场", "#43A047"),
    INDUSTRY("行业公司", "#FB8C00"),
    POLICY("政策监管", "#8E24AA"),
    INTERNATIONAL("国际财经", "#E53935")
}

// 新闻状态
enum class NewsImportance {
    HIGH, MEDIUM, LOW
}

// 新闻数据模型
data class News(
    val id: String,
    val title: String,
    val summary: String,
    val background: String?,
    val impact: String?,
    val category: NewsCategory,
    val importance: NewsImportance,
    val keywords: List<String>,
    val source: String,
    val sourceUrl: String?,
    val publishTime: String,
    val updateTime: String,
    val imageUrl: String?,
    val isFavorite: Boolean = false,
    val readCount: Int = 0
)

// 快讯模型
data class FlashNews(
    val id: String,
    val title: String,
    val content: String,
    val category: NewsCategory,
    val publishTime: String,
    val isImportant: Boolean = false
)

// 专题模型
data class Topic(
    val id: String,
    val title: String,
    val description: String,
    val coverImage: String?,
    val newsCount: Int,
    val updateTime: String,
    val relatedNews: List<String>
)

// 晚报模型
data class EveningReport(
    val date: String,
    val title: String,
    val summary: String,
    val hotNews: List<News>,
    val marketOverview: MarketOverview,
    val tips: List<String>
)

data class MarketOverview(
    val shanghai: IndexData,
    val shenzhen: IndexData,
    val nasdaq: IndexData,
    val gold: CommodityData,
    val oil: CommodityData
)

data class IndexData(
    val name: String,
    val value: Double,
    val change: Double,
    val changePercent: Double
)

data class CommodityData(
    val name: String,
    val value: Double,
    val unit: String,
    val change: Double,
    val changePercent: Double
)

// API 响应模型
data class NewsResponse(
    val status: String,
    val data: List<News>,
    val total: Int
)

data class FlashNewsResponse(
    val status: String,
    val data: List<FlashNews>
)

data class TopicsResponse(
    val status: String,
    val data: List<Topic>
)

// 用户偏好设置
data class UserPreferences(
    val isDarkMode: Boolean = false,
    val pushEnabled: Boolean = true,
    val pushTime: String = "20:00",
    val followedCategories: Set<NewsCategory> = NewsCategory.values().toSet(),
    val followedKeywords: Set<String> = emptySet()
)