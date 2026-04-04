package com.example.limitlesstech.limitlessnews.domain.repository

import com.example.limitlesstech.limitlessnews.core.util.Result
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle


interface NewsRepository{
    suspend fun getNews(country:String,
                        category:String): Result<List<NewsArticle>>
}