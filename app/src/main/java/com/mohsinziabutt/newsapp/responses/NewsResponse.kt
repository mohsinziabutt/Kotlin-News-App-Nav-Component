package com.mohsinziabutt.newsapp.responses

import com.mohsinziabutt.newsapp.responseModels.newsModel.NewsArticleModel

data class NewsResponse(
    val status: String?,
    var articles: ArrayList<NewsArticleModel>
)