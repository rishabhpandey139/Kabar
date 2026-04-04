package com.example.limitlesstech.limitlessnews.domain.model

import java.io.Serializable


data class NewsArticle(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val source: String,
    val date: String,
    val link: String

): Serializable

//This is what your app actually uses