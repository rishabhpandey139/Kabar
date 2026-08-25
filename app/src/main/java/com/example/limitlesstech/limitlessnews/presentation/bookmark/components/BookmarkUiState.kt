package com.example.limitlesstech.limitlessnews.presentation.bookmark.components

import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle

data class BookmarkUiState(
    val isLoading: Boolean = true,
    val bookmarks: List<NewsArticle> = emptyList(),
    val searchQuery: String = ""
) {

    val filteredBookmarks: List<NewsArticle>
        get() {

            if (searchQuery.isBlank()) {
                return bookmarks
            }

            val query = searchQuery.trim()

            return bookmarks.filter { article ->

                article.title.contains(
                    other = query,
                    ignoreCase = true
                ) ||
                        article.source.contains(
                            other = query,
                            ignoreCase = true
                        )
            }
        }
}