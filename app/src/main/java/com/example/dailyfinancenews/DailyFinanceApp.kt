package com.example.dailyfinancenews

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class DailyFinanceApp : Application() {
    
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }
    
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "finance_news",
                "财经资讯",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "每日财经热点推送"
            }
            
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}