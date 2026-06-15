package com.example.limitlesstech.limitlessnews.domain.repository

import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {

    suspend fun toggleBookmark(
        article: NewsArticle
    )

    fun isBookmarked(
        id: String
    ): Flow<Boolean>

    fun getBookmarkedArticles():
            Flow<List<NewsArticle>>

    fun getBookmarkedArticleById(
        id: String
    ): Flow<NewsArticle?>
}