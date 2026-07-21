package com.example.limitlesstech.limitlessnews.domain.model

//This sealed class represents all possible outcomes when the user taps Read Full Article,
sealed interface OpenArticleAction {

    data class OpenBrowser(
        val url: String
    ) : OpenArticleAction

    data object ShowOfflineArticle : OpenArticleAction

    data object ShowNoInternetMessage : OpenArticleAction
}