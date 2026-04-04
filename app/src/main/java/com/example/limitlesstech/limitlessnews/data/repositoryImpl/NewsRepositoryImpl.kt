package com.example.limitlesstech.limitlessnews.data.repositoryImpl



import com.example.limitlesstech.limitlessnews.core.network.NewsApi
import javax.inject.Inject

import com.example.limitlesstech.limitlessnews.core.util.Result
import com.example.limitlesstech.limitlessnews.data.mapper.toDomain

import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle

import com.example.limitlesstech.limitlessnews.domain.repository.NewsRepository

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) : NewsRepository {

    override suspend fun getNews(
        country: String,
        category: String
    ): Result<List<NewsArticle>> {

        return try {
            val response = api.getNews(country, category)

            val articles = response.articles.map {
                it.toDomain()
            }

            Result.Success(articles)

        } catch (e: Exception) {
            Result.Failure(e.message ?: "Something went wrong")
        }
    }
}