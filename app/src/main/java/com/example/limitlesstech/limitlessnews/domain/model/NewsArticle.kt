package com.example.limitlesstech.limitlessnews.domain.model


import kotlinx.serialization.Serializable



@Serializable
data class NewsArticle(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val source: String,
    val date: String,
    val link: String,
    val content:String

)

//This is what your app actually uses