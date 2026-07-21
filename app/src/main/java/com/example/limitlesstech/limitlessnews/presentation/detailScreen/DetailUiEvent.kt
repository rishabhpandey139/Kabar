package com.example.limitlesstech.limitlessnews.presentation.detailScreen

sealed interface DetailUiEvent {

    data class OpenBrowser(
        val url: String
    ) : DetailUiEvent

    data class ShowSnackbar(
        val message: String
    ) : DetailUiEvent
}