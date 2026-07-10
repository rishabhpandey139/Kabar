package com.example.limitlesstech.limitlessnews.presentation.home

import com.example.limitlesstech.limitlessnews.domain.common.DomainError
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle

data class HomeUiState(

    /**
     * Article displayed in the  TrendingCard.
     */
    val trendingArticle: NewsArticle? = null,

    /**

     */
    val news: List<NewsArticle> = emptyList(),

    /**
     * Home loading state.
     */
    val isLoading: Boolean = true,

    /**
     * Error from Result sealed class.
     */
    val error: DomainError? = null
)