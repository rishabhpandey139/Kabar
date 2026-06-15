package com.example.limitlesstech.limitlessnews.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.limitlesstech.limitlessnews.data.local.datastore.DataStoreManager
import com.example.limitlesstech.limitlessnews.domain.common.DomainError
import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.domain.model.NewsFilter
import com.example.limitlesstech.limitlessnews.domain.usecase.news.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val news: List<NewsArticle> = emptyList(),
    val isLoading: Boolean = true,
    val error: DomainError? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val dataStore: DataStoreManager
) : ViewModel() {

    companion object {

        private var cachedNews: List<NewsArticle> =
            emptyList()
    }

    private val _uiState =
        MutableStateFlow(
            HomeUiState(
                news = cachedNews,
                isLoading = cachedNews.isEmpty()
            )
        )

    val uiState: StateFlow<HomeUiState> =
        _uiState

    init {


        if (cachedNews.isEmpty()) {

            loadInitialNews()

        } else {



            _uiState.update {
                it.copy(
                    news = cachedNews,
                    isLoading = false
                )
            }
        }
    }

    private fun loadInitialNews() {

        viewModelScope.launch {

            val country =
                dataStore.country.first()

            val topic =
                dataStore.topic.first()

            val sources =
                dataStore.sources.first()

            loadNews(
                NewsFilter(
                    country = country,
                    category = topic,
                    sources = sources
                )
            )
        }
    }

    private fun loadNews(
        filter: NewsFilter
    ) {

        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            when (val result = getNewsUseCase(filter)) {

                is Result.Success -> {

                    cachedNews = result.data

                    _uiState.update {
                        it.copy(
                            news = cachedNews,
                            isLoading = false
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


    }
}