package com.example.limitlesstech.limitlessnews.presentation.navigation

import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import kotlinx.serialization.Serializable


@Serializable
sealed  class Routes{

@Serializable
data object Home: Routes()

@Serializable
data class Details(val articleId: String): Routes()

}