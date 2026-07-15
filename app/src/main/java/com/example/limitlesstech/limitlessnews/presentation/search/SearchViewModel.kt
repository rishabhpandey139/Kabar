package com.example.limitlesstech.limitlessnews.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.domain.usecase.news.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class SearchViewModel @Inject constructor(

    private val searchNewsUseCase: SearchNewsUseCase

) : ViewModel() {

    private val query = MutableStateFlow("")

    val uiState: StateFlow<SearchUiState> =
        query
            .map {

                SearchUiState(
                    query = it,
                    isSearching = it.isNotBlank()
                )

            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = SearchUiState()
            )

    val searchResults: Flow<PagingData<NewsArticle>> =
        query

            .debounce(500)

            .distinctUntilChanged()

            .flatMapLatest { searchQuery ->

                searchNewsUseCase(searchQuery)

            }
            .cachedIn(viewModelScope)

    fun onQueryChange(
        newQuery: String
    ) {

        query.value = newQuery

    }
}