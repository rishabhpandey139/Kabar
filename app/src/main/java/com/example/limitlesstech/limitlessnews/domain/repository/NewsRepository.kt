package com.example.limitlesstech.limitlessnews.domain.repository

import androidx.paging.PagingData
import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.domain.model.NewsFilter
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getTrendingNews(
        filter: NewsFilter
    ): Result<NewsArticle?>


    fun getPagedNews(
        filter: NewsFilter
    ): Flow<PagingData<NewsArticle>>


}