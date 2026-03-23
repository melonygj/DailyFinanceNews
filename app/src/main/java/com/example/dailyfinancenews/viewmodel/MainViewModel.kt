package com.example.dailyfinancenews.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyfinancenews.data.model.*
import com.example.dailyfinancenews.data.repository.FinanceRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate

class MainViewModel : ViewModel() {
    
    private val repository = FinanceRepository()
    
    // 首页数据
    var dailyNews = mutableStateListOf<News>()
        private set
    
    var flashNews = mutableStateListOf<FlashNews>()
        private set
    
    var eveningReport = mutableStateOf<EveningReport?>(null)
        private set
    
    var topics = mutableStateListOf<Topic>()
        private set
    
    // UI 状态
    var isLoading = mutableStateOf(false)
        private set
    
    var selectedCategory = mutableStateOf<NewsCategory?>(null)
        private set
    
    var isDarkMode = mutableStateOf(false)
        private set
    
    // 收藏列表
    var favorites = mutableStateListOf<News>()
        private set
    
    // 当前选中的底部导航
    var currentTab = mutableStateOf(0)
        private set
    
    init {
        loadDailyNews()
        loadFlashNews()
        loadTopics()
        loadEveningReport()
    }
    
    fun loadDailyNews(category: NewsCategory? = null) {
        viewModelScope.launch {
            isLoading.value = true
            repository.getDailyNews(category = category).collectLatest { result ->
                isLoading.value = false
                result.fold(
                    onSuccess = { news ->
                        dailyNews.clear()
                        dailyNews.addAll(news)
                    },
                    onFailure = { /* 错误处理 */ }
                )
            }
        }
    }
    
    fun loadFlashNews() {
        viewModelScope.launch {
            repository.getFlashNews().collectLatest { result ->
                result.fold(
                    onSuccess = { news ->
                        flashNews.clear()
                        flashNews.addAll(news)
                    },
                    onFailure = { }
                )
            }
        }
    }
    
    fun loadTopics() {
        viewModelScope.launch {
            repository.getTopics().collectLatest { result ->
                result.fold(
                    onSuccess = { topicList ->
                        topics.clear()
                        topics.addAll(topicList)
                    },
                    onFailure = { }
                )
            }
        }
    }
    
    fun loadEveningReport(date: LocalDate = LocalDate.now()) {
        viewModelScope.launch {
            repository.getEveningReport(date).collectLatest { result ->
                result.fold(
                    onSuccess = { report ->
                        eveningReport.value = report
                    },
                    onFailure = { }
                )
            }
        }
    }
    
    fun selectCategory(category: NewsCategory?) {
        selectedCategory.value = category
        loadDailyNews(category)
    }
    
    fun toggleDarkMode() {
        isDarkMode.value = !isDarkMode.value
    }
    
    fun toggleFavorite(news: News) {
        val index = favorites.indexOfFirst { it.id == news.id }
        if (index >= 0) {
            favorites.removeAt(index)
        } else {
            favorites.add(news)
        }
    }
    
    fun isFavorite(news: News): Boolean {
        return favorites.any { it.id == news.id }
    }
    
    fun setCurrentTab(tab: Int) {
        currentTab.value = tab
    }
    
    fun refresh() {
        loadDailyNews(selectedCategory.value)
        loadFlashNews()
        loadTopics()
    }
}