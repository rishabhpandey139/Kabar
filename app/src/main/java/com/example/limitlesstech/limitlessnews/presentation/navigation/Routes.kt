package com.example.limitlesstech.limitlessnews.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object UserSelection : Routes()

    @Serializable
    data object Country : Routes()

    @Serializable
    data object Topic : Routes()

    @Serializable
    data object Source : Routes()

    @Serializable
    data object Home : Routes()

    @Serializable
    data class Details(val articleId: String) : Routes()
}