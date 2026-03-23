# 每日财经热点 App

一款面向财经信息关注用户的安卓移动应用，定位为"每日晚间财经热点总结平台"。

## 功能特性

### 核心功能
- 📰 **每日热点** - 每日财经新闻集中整理与更新
- ⚡ **实时快讯** - 滚动更新的财经资讯
- 📊 **专题追踪** - 持续关注的财经事件聚合
- ⭐ **收藏功能** - 重要新闻一键收藏
- 🌙 **夜间模式** - 保护眼睛的暗色主题
- 📢 **定时推送** - 每晚间定时推送晚报

### 新闻分类
- 宏观经济
- 资本市场
- 行业公司
- 政策监管
- 国际财经

### 新闻详情
每条资讯包含：
- 新闻标题
- 核心摘要
- 事件背景
- 市场影响分析
- 关键词标签

## 技术栈

- **语言**: Kotlin
- **UI 框架**: Jetpack Compose + Material 3
- **架构**: MVVM
- **网络**: Retrofit + OkHttp
- **图片**: Coil
- **数据存储**: DataStore
- **后台任务**: WorkManager

## 项目结构

```
app/src/main/java/com/example/dailyfinancenews/
├── data/
│   ├── api/          # API 接口
│   ├── model/        # 数据模型
│   └── repository/   # 数据仓库
├── ui/
│   ├── components/   # UI 组件
│   ├── screens/      # 页面
│   └── theme/        # 主题配置
├── viewmodel/        # ViewModel
├── service/          # 后台服务
└── MainActivity.kt
```

## 界面导航

底部导航包含四个模块：
1. **首页** - 每日重点内容
2. **快讯** - 滚动更新资讯
3. **专题** - 财经事件聚合
4. **我的** - 设置和收藏

## 构建 APK

### 使用 GitHub Actions
1. Fork 本仓库
2. 进入 Actions 页面
3. 运行 "Build APK" 工作流
4. 下载生成的 APK

### 使用 Android Studio
1. 克隆项目
2. 用 Android Studio 打开
3. 等待 Gradle 同步
4. Build → Build Bundle(s) / APK(s) → Build APK(s)

## API 配置

修改 `FinanceApiService.kt` 中的 `BASE_URL` 配置你的后端 API 地址。

## 隐私权限

- `INTERNET` - 网络访问
- `ACCESS_NETWORK_STATE` - 网络状态
- `POST_NOTIFICATIONS` - 推送通知
- `SCHEDULE_EXACT_ALARM` - 定时任务

## License

MIT License