package com.mohsinziabutt.newsapp.responseModels.newsModel

data class NewsArticleModel(
    val source: NewsSourceModel,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)