package com.example.limitlesstech.limitlessnews.data.mapper

import com.example.limitlesstech.limitlessnews.data.remote.dto.Article

import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle


//it used to convert messy api data to clean data that we can use in our app
fun Article.toDomain(): NewsArticle {
    return NewsArticle(
        id = url?: "",
        title = title ?: "No Title",
        description = description ?: "No Description",
        imageUrl = urlToImage ?: "https://via.placeholder.com/300",
        source = source?.name ?:"Unknown Source",
        date = publishedAt?: "",
        link = url ?: "",
        content= content ?: ""
    )
}