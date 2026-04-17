package com.example.limitlesstech.limitlessnews.domain.model

data class NewsFilter(
    val country: String? = null,          // e.g. "us"
    val category: String? = null,         // e.g. "health"
    val sources: Set<String> = emptySet() // e.g. setOf("wired", "bbc-news")
)