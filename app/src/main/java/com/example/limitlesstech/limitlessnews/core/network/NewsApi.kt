package com.example.limitlesstech.limitlessnews.core.network

import com.example.limitlesstech.limitlessnews.data.remote.dto.NewsDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class NewsApi @Inject constructor(
    private val client: HttpClient,
) {
    suspend fun getTopHeadlines(
        country: String?,
        category: String?,
        sources: String?
    ): NewsDto {
        return client.get("top-headlines") {
            parameter("apikey", "c41c23cd12b043d095bb3f9ae5335960")

            if (!sources.isNullOrBlank()) {
                // NewsAPI rule: sources cannot be mixed with country/category
                parameter("sources", sources)
            } else {
                if (!country.isNullOrBlank()) parameter("country", country)
                if (!category.isNullOrBlank()) parameter("category", category)
            }
        }.body()
    }
}