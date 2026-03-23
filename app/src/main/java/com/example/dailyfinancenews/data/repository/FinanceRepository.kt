package com.example.dailyfinancenews.data.repository

import com.example.dailyfinancenews.data.api.ApiClient
import com.example.dailyfinancenews.data.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FinanceRepository {
    
    private val apiService = ApiClient.create()
    
    fun getDailyNews(
        date: LocalDate = LocalDate.now(),
        category: NewsCategory? = null
    ): Flow<Result<List<News>>> = flow {
        try {
            val response = apiService.getDailyNews(
                date = date.format(DateTimeFormatter.ISO_DATE),
                category = category?.name
            )
            if (response.isSuccessful && response.body() != null) {
                emit(Result.success(response.body()!!.data))
            } else {
                // 使用模拟数据
                emit(Result.success(getMockDailyNews()))
            }
        } catch (e: Exception) {
            emit(Result.success(getMockDailyNews()))
        }
    }.flowOn(Dispatchers.IO)
    
    fun getFlashNews(limit: Int = 50): Flow<Result<List<FlashNews>>> = flow {
        try {
            val response = apiService.getFlashNews(limit)
            if (response.isSuccessful && response.body() != null) {
                emit(Result.success(response.body()!!.data))
            } else {
                emit(Result.success(getMockFlashNews()))
            }
        } catch (e: Exception) {
            emit(Result.success(getMockFlashNews()))
        }
    }.flowOn(Dispatchers.IO)
    
    fun getTopics(): Flow<Result<List<Topic>>> = flow {
        try {
            val response = apiService.getTopics()
            if (response.isSuccessful && response.body() != null) {
                emit(Result.success(response.body()!!.data))
            } else {
                emit(Result.success(getMockTopics()))
            }
        } catch (e: Exception) {
            emit(Result.success(getMockTopics()))
        }
    }.flowOn(Dispatchers.IO)
    
    fun getEveningReport(date: LocalDate = LocalDate.now()): Flow<Result<EveningReport>> = flow {
        try {
            val response = apiService.getEveningReport(date.format(DateTimeFormatter.ISO_DATE))
            if (response.isSuccessful && response.body() != null) {
                emit(Result.success(response.body()!!))
            } else {
                emit(Result.success(getMockEveningReport()))
            }
        } catch (e: Exception) {
            emit(Result.success(getMockEveningReport()))
        }
    }.flowOn(Dispatchers.IO)
    
    // 模拟数据
    private fun getMockDailyNews(): List<News> {
        return listOf(
            News(
                id = "1",
                title = "央行宣布新一轮降准措施",
                summary = "中国人民银行今日宣布下调存款准备金率0.5个百分点，释放长期资金约1万亿元。",
                background = "此次降准是继去年以来的第三次，旨在支持实体经济发展。",
                impact = "降准将增加银行体系流动性，降低企业融资成本，利好银行、房地产等行业。",
                category = NewsCategory.MACRO,
                importance = NewsImportance.HIGH,
                keywords = listOf("降准", "央行", "流动性"),
                source = "新华网",
                sourceUrl = null,
                publishTime = "2026-03-23T08:00:00",
                updateTime = "2026-03-23T08:30:00",
                imageUrl = null
            ),
            News(
                id = "2",
                title = "A股三大指数集体收涨",
                summary = "今日A股三大指数均涨超1%，成交额突破1.5万亿，北向资金净流入超200亿。",
                background = "市场情绪回暖，科技股领涨两市。",
                impact = "市场信心恢复，短期有望继续上攻。",
                category = NewsCategory.MARKET,
                importance = NewsImportance.HIGH,
                keywords = listOf("A股", "指数", "北向资金"),
                source = "东方财富",
                sourceUrl = null,
                publishTime = "2026-03-23T15:00:00",
                updateTime = "2026-03-23T15:30:00",
                imageUrl = null
            ),
            News(
                id = "3",
                title = "新能源汽车销量再创新高",
                summary = "3月新能源汽车销量突破100万辆，同比增长45%，渗透率突破40%。",
                background = "政策持续发力，消费者接受度不断提高。",
                impact = "产业链景气度持续，相关概念股值得关注。",
                category = NewsCategory.INDUSTRY,
                importance = NewsImportance.MEDIUM,
                keywords = listOf("新能源", "汽车", "销量"),
                source = "汽车之家",
                sourceUrl = null,
                publishTime = "2026-03-23T10:00:00",
                updateTime = "2026-03-23T11:00:00",
                imageUrl = null
            )
        )
    }
    
    private fun getMockFlashNews(): List<FlashNews> {
        return listOf(
            FlashNews("1", "美联储维持利率不变", "美联储宣布维持基准利率不变，符合市场预期。", NewsCategory.INTERNATIONAL, "2026-03-23T02:00:00", true),
            FlashNews("2", "科创板新规出台", "上交所发布科创板注册制新规，优化发行上市条件。", NewsCategory.POLICY, "2026-03-23T09:30:00", false),
            FlashNews("3", "国际油价震荡", "布伦特原油价格今日震荡，收于85美元/桶。", NewsCategory.INTERNATIONAL, "2026-03-23T16:00:00", false)
        )
    }
    
    private fun getMockTopics(): List<Topic> {
        return listOf(
            Topic("1", "2026年两会专题", "聚焦2026年全国两会，解读政府工作报告与政策走向。", null, 25, "2026-03-23", emptyList()),
            Topic("2", "AI产业变革", "人工智能加速落地，产业格局深度重塑。", null, 18, "2026-03-23", emptyList()),
            Topic("3", "碳中和进程", "双碳目标推进，绿色能源迎来发展机遇。", null, 15, "2026-03-23", emptyList())
        )
    }
    
    private fun getMockEveningReport(): EveningReport {
        return EveningReport(
            date = "2026-03-23",
            title = "3月23日财经晚报",
            summary = "今日市场整体表现积极，央行降准利好市场信心，A股三大指数集体收涨。",
            hotNews = getMockDailyNews(),
            marketOverview = MarketOverview(
                shanghai = IndexData("上证指数", 3500.0, 50.0, 1.45),
                shenzhen = IndexData("深证成指", 12000.0, 180.0, 1.52),
                nasdaq = IndexData("纳斯达克", 20500.0, 200.0, 0.98),
                gold = CommodityData("黄金", 4800.0, "元/克", 20.0, 0.42),
                oil = CommodityData("原油", 580.0, "元/桶", 5.0, 0.87)
            ),
            tips = listOf("关注明日的PMI数据公布", "美联储官员将发表讲话", "本周将公布工业企业利润数据")
        )
    }
}