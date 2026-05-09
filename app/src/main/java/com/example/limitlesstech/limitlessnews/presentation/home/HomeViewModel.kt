package com.example.limitlesstech.limitlessnews.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.limitlesstech.limitlessnews.domain.common.DomainError
import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.domain.model.NewsFilter
import com.example.limitlesstech.limitlessnews.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val news: List<NewsArticle> = emptyList(),
    val isLoading: Boolean = false,
    val error: DomainError? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun loadNews(filter: NewsFilter) {
        viewModelScope.launch {

            // 🔥 Start loading
            _uiState.update {
                it.copy(isLoading = true, error = null)
            }

            val result = getNewsUseCase(filter)

            when (result) {

                is Result.Success -> {
                    _uiState.update {
                        it.copy(
                            news = result.data,
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