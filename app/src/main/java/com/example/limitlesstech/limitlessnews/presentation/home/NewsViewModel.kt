package com.example.limitlesstech.limitlessnews.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.limitlesstech.limitlessnews.core.util.Result
import kotlinx.coroutines.launch

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
): ViewModel() {
    var state by mutableStateOf<Result<List<NewsArticle>>>(Result.Idle)
        private set

    fun fetchNews(country: String, category: String) {

        viewModelScope.launch {
            state = Result.Loading
            state= getNewsUseCase(country, category)
        }
    }

}