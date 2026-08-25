package com.example.limitlesstech.limitlessnews.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object Splash : Routes()

    @Serializable
    data object Onboarding : Routes()

    @Serializable
    data object SignUp : Routes()

    @Serializable
    data object Login : Routes()

    @Serializable
    data object Forgot : Routes()

    @Serializable
    data object UserSelection : Routes()

    @Serializable
    data object Country : Routes()

    @Serializable
    data object Topic : Routes()

    @Serializable
    data object Source : Routes()

    @Serializable
    data object MainGraph : Routes()

    @Serializable
    data object Home : Routes()

    @Serializable
    data object Bookmark : Routes()

    @Serializable
    data object FillProfile : Routes()

    @Serializable
    data object EditProfile : Routes()

    @Serializable
    data class Details(
        val articleId: String
    ) : Routes()
}