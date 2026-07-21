package com.example.limitlesstech.limitlessnews.presentation.bookmark

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.domain.usecase.bookmark.GetBookmarkByIdUseCase
import com.example.limitlesstech.limitlessnews.domain.usecase.bookmark.GetBookmarksUseCase
import com.example.limitlesstech.limitlessnews.domain.usecase.bookmark.IsBookmarkedUseCase
import com.example.limitlesstech.limitlessnews.domain.usecase.bookmark.ToggleBookmarkUseCase
import com.example.limitlesstech.limitlessnews.presentation.bookmark.components.BookmarkUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
    private val isBookmarkedUseCase: IsBookmarkedUseCase,
    private val getBookmarksUseCase: GetBookmarksUseCase,
    private val getBookmarkByIdUseCase: GetBookmarkByIdUseCase
) : ViewModel() {

    companion object {
        private var cachedBookmarks:
                List<NewsArticle> = emptyList()

        private var hasLoadedBookmarks =
            false
    }

    private val _uiState =
        MutableStateFlow(
            BookmarkUiState(
                isLoading = !hasLoadedBookmarks,
                bookmarks = cachedBookmarks
            )
        )

    val uiState: StateFlow<BookmarkUiState> =
        _uiState.asStateFlow()

    init {

        observeBookmarks()
    }

    private fun observeBookmarks() {

        viewModelScope.launch {

            getBookmarksUseCase()
                .collectLatest { bookmarks ->

                    cachedBookmarks = bookmarks

                    hasLoadedBookmarks = true

                    _uiState.update {

                        it.copy(
                            isLoading = false,
                            bookmarks = cachedBookmarks
                        )
                    }
                }
        }
    }

    fun toggleBookmark(
        article: NewsArticle
    ) {

        viewModelScope.launch {

            toggleBookmarkUseCase(article)
        }
    }

    fun isBookmarked(
        id: String
    ) = isBookmarkedUseCase(id)

    fun getBookmarkById(
        id: String
    ) = getBookmarkByIdUseCase(id)
}