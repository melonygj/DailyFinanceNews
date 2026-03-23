package com.example.dailyfinancenews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dailyfinancenews.ui.components.BottomNavBar
import com.example.dailyfinancenews.ui.screens.*
import com.example.dailyfinancenews.ui.theme.DailyFinanceNewsTheme
import com.example.dailyfinancenews.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = viewModel()
            
            DailyFinanceNewsTheme(darkTheme = viewModel.isDarkMode.value) {
                Scaffold(
                    bottomBar = {
                        BottomNavBar(
                            currentTab = viewModel.currentTab.value,
                            onTabSelected = { viewModel.setCurrentTab(it) }
                        )
                    }
                ) { paddingValues ->
                    when (viewModel.currentTab.value) {
                        0 -> HomeScreen(viewModel, Modifier.padding(paddingValues))
                        1 -> FlashScreen(viewModel, Modifier.padding(paddingValues))
                        2 -> TopicsScreen(viewModel, Modifier.padding(paddingValues))
                        3 -> ProfileScreen(viewModel, Modifier.padding(paddingValues))
                    }
                }
            }
        }
    }
}