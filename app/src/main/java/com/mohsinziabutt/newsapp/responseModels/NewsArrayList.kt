package com.mohsinziabutt.newsapp.responseModels

import com.mohsinziabutt.newsapp.responseModels.newsModel.NewsSourceModel

data class NewsArrayList(
    val news_source: NewsSourceModel,
    val news_author: String?,
    val news_title: String?,
    val news_description: String?,
    val news_url: String?,
    val news_urlToImage: String?,
    val news_publishedAt: String?,
    val news_content: String?
)