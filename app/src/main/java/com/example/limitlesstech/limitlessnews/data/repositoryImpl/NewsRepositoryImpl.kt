package com.example.limitlesstech.limitlessnews.data.repositoryImpl

import com.example.limitlesstech.limitlessnews.core.network.NewsApi
import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.common.DomainError
import com.example.limitlesstech.limitlessnews.data.mapper.toDomain
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.domain.model.NewsFilter
import com.example.limitlesstech.limitlessnews.domain.repository.NewsRepository
import java.io.IOException
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

            Result.Failure(mapError(e)) // ✅ FIXED
        }
    }

    // 🔥 Proper error mapping (same as Auth)
    private fun mapError(e: Exception): DomainError {
        return when (e) {
            is IOException -> DomainError.Network
            else -> DomainError.Unknown(e.message)
        }
    }
}