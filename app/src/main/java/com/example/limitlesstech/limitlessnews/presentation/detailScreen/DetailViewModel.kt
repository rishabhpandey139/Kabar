package com.example.limitlesstech.limitlessnews.presentation.detailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.limitlesstech.limitlessnews.domain.model.OpenArticleAction
import com.example.limitlesstech.limitlessnews.domain.usecase.detail.OpenArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val openArticleUseCase: OpenArticleUseCase
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<DetailUiEvent>()
    val uiEvent: SharedFlow<DetailUiEvent> = _uiEvent.asSharedFlow()

    fun onReadFullArticleClicked(
        articleUrl: String,
        isBookmarked: Boolean
    ) {
        viewModelScope.launch {

            when (val action = openArticleUseCase(articleUrl, isBookmarked)) {

                is OpenArticleAction.OpenBrowser -> {
                    _uiEvent.emit(
                        DetailUiEvent.OpenBrowser(action.url)
                    )
                }

                OpenArticleAction.ShowOfflineArticle -> {
                    _uiEvent.emit(
                        DetailUiEvent.ShowSnackbar(
                            "You're offline. Showing saved article."
                        )
                    )
                }

                OpenArticleAction.ShowNoInternetMessage -> {
                    _uiEvent.emit(
                        DetailUiEvent.ShowSnackbar(
                            "Internet required to open publisher article."
                        )
                    )
                }
            }
        }
    }
}