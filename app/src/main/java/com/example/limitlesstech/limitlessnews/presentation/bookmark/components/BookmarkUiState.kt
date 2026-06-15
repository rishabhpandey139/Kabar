package com.example.limitlesstech.limitlessnews.presentation.bookmark.components

import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle

data class BookmarkUiState (
    val isLoading:Boolean = true,
     val bookmarks:List<NewsArticle> =emptyList()
)