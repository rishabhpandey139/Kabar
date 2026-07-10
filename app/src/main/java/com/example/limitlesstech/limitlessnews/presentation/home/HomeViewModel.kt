package com.example.limitlesstech.limitlessnews.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.limitlesstech.limitlessnews.data.local.datastore.DataStoreManager
import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.domain.model.NewsFilter
import com.example.limitlesstech.limitlessnews.domain.usecase.news.GetPagedNewsUseCase
import com.example.limitlesstech.limitlessnews.domain.usecase.news.GetTrendingNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

    private val getTrendingNewsUseCase: GetTrendingNewsUseCase,

    private val getPagedNewsUseCase: GetPagedNewsUseCase,

    private val dataStore: DataStoreManager

) : ViewModel() {

    companion object {
        private var cachedTrending: NewsArticle? = null
    }

    private val _uiState = MutableStateFlow(
        HomeUiState(
            trendingArticle = cachedTrending,
            isLoading = cachedTrending == null
        )
    )

    val uiState: StateFlow<HomeUiState> = _uiState

    private val _pagedNews =
        MutableStateFlow<Flow<PagingData<NewsArticle>>>(emptyFlow())

    val pagedNews: StateFlow<Flow<PagingData<NewsArticle>>> =
        _pagedNews

    init {
        loadInitialNews()
    }

    private fun loadInitialNews() {

        viewModelScope.launch {

            val filter = NewsFilter(
                country = dataStore.country.first(),
                category = dataStore.topic.first(),
                sources = dataStore.sources.first()
            )

            loadTrendingNews(filter)

            _pagedNews.value =
                getPagedNewsUseCase(filter)
                    .cachedIn(viewModelScope)
        }
    }

    private suspend fun loadTrendingNews(
        filter: NewsFilter
    ) {

        if (cachedTrending != null) {

            _uiState.update {

                it.copy(
                    trendingArticle = cachedTrending,
                    isLoading = false
                )
            }

            return
        }

        when (val result = getTrendingNewsUseCase(filter)) {

            is Result.Success -> {

                cachedTrending = result.data

                _uiState.update {

                    it.copy(
                        trendingArticle = result.data,
                        isLoading = false,
                        error = null
                    )
                }
            }

            is Result.Failure -> {

                _uiState.update {

                    it.copy(
                        isLoading = false,
                        error = result.error
                    )
                }
            }
        }
    }
    fun refresh() {

        viewModelScope.launch {

            cachedTrending = null

            val filter = NewsFilter(
                country = dataStore.country.first(),
                category = dataStore.topic.first(),
                sources = dataStore.sources.first()
            )

            loadTrendingNews(filter)

            _pagedNews.value =
                getPagedNewsUseCase(filter)
                    .cachedIn(viewModelScope)
        }
    }
}