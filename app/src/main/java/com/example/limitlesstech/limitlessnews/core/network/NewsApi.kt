package com.example.limitlesstech.limitlessnews.core.network

import com.example.limitlesstech.limitlessnews.data.remote.dto.NewsDto
import com.example.limitlesstech.limitlessnews.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class NewsApi @Inject constructor(
    private val client: HttpClient,
) {

    /**
     * Trending API
     * Used only for Trending Card
     */
    suspend fun getTrendingNews(
        country: String?,
        category: String?,
        sources: String?

    ): NewsDto {

        return client.get("top-headlines") {
            parameter("apikey", BuildConfig.NEWS_API_KEY)

            // Only one article is required
            parameter("page", 1)
            parameter("pageSize", 1)

            if (!sources.isNullOrBlank()) {

                parameter("sources", sources)

            } else {

                if (!country.isNullOrBlank()) {
                    parameter("country", country)
                }

                if (!category.isNullOrBlank()) {
                    parameter("category", category)
                }
            }

        }.body()
    }

    /**
     * Paging API
     * Used for infinite scrolling for home feed
     */
    suspend fun getPagedNews(
        category: String?,
        sources: String?,
        page: Int,
        pageSize: Int
    ): NewsDto {

        return client.get("everything") {

            parameter("apikey", BuildConfig.NEWS_API_KEY)
            parameter("page", page)
            parameter("pageSize", pageSize)

            if (!sources.isNullOrBlank()) {

                parameter("sources", sources)
                // Optional
                parameter(
                    "language",
                    "en"
                )

            } else {

                /**
                 * everything endpoint requires q
                 */
                parameter(
                    "q",
                    category ?: "news"
                )

                parameter(
                    "sortBy",
                    "publishedAt"
                )

                // Optional
                parameter(
                    "language",
                    "en"
                )
            }

        }.body()
    }
    /**
     * Search News API
     * Used only for Search
     */
    suspend fun searchNews(
        query: String,
        page: Int,
        pageSize: Int
    ): NewsDto {

        return client.get("everything") {

            parameter("apikey", BuildConfig.NEWS_API_KEY)

            parameter("q", query)

            parameter("page", page)

            parameter("pageSize", pageSize)

            parameter("sortBy", "publishedAt")

            parameter("language", "en")

        }.body()
    }

}