package com.example.limitlesstech.limitlessnews.domain.repository

import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.domain.model.NewsFilter

interface NewsRepository {
    suspend fun getNews(filter: NewsFilter): Result<List<NewsArticle>>
}