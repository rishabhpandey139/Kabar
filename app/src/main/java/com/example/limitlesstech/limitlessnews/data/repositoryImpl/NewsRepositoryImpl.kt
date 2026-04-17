package com.example.limitlesstech.limitlessnews.data.repositoryImpl

import com.example.limitlesstech.limitlessnews.core.network.NewsApi
import com.example.limitlesstech.limitlessnews.core.util.Result
import com.example.limitlesstech.limitlessnews.data.mapper.toDomain
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.domain.model.NewsFilter
import com.example.limitlesstech.limitlessnews.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) : NewsRepository {

    override suspend fun getNews(filter: NewsFilter): Result<List<NewsArticle>> {
        return try {
            val sourcesParam = filter.sources
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .joinToString(",")

            val response = api.getTopHeadlines(
                country = filter.country,
                category = filter.category,
                sources = sourcesParam.ifBlank { null }
            )

            val articles = response.articles.map { it.toDomain() }
            Result.Success(articles)
        } catch (e: Exception) {
            Result.Failure(e.message ?: "Something went wrong")
        }
    }
}