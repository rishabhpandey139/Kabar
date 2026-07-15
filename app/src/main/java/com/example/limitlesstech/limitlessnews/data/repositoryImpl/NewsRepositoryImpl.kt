package com.example.limitlesstech.limitlessnews.data.repositoryImpl

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.limitlesstech.limitlessnews.core.network.NewsApi
import com.example.limitlesstech.limitlessnews.data.mapper.toDomain
import com.example.limitlesstech.limitlessnews.data.paging.NewsPagingSource
import com.example.limitlesstech.limitlessnews.domain.common.DomainError
import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.domain.model.NewsFilter
import com.example.limitlesstech.limitlessnews.domain.repository.NewsRepository
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject
import com.example.limitlesstech.limitlessnews.data.paging.SearchPagingSource

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) : NewsRepository {

    /**
     * Trending News - This function gets only the first (top/trending) news article from the API.
     */
    override suspend fun getTrendingNews(
        filter: NewsFilter
    ): Result<NewsArticle?> {

        return try {

            val sourcesParam = filter.sources
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .joinToString(",")

            Log.d("TRENDING_API", "Sources: $sourcesParam")

            val response = api.getTrendingNews(
                country = filter.country,
                category = filter.category,
                sources = sourcesParam.ifBlank { null }
            )

            response.articles.forEachIndexed { index, article ->
                Log.d(
                    "TRENDING_RESPONSE",
                    "$index -> ${article.source?.name} | ${article.title}"
                )
            }

            val trendingArticle = response
                .articles
                .firstOrNull()
                ?.toDomain()

            Result.Success(trendingArticle)

        } catch (e: Exception) {

            Result.Failure(mapError(e))
        }
    }

    /**
     * Infinite Paging News
     */
    //This function loads news page by page instead of loading all news at once.
    override fun getPagedNews(
        filter: NewsFilter
    ): Flow<PagingData<NewsArticle>> {

        return Pager(// Load the next page whenever the user scrolls.

            config = PagingConfig(//sets the paging rules
                pageSize = 20,// Load 20 news articles in one API call.
                initialLoadSize = 20, //When the screen opens, load the first 20 articles.
                prefetchDistance = 5,//When only 5 articles are left, automatically load the next page.
                enablePlaceholders = false //Don't show empty placeholder items for articles that haven't loaded yet.
            ),

            pagingSourceFactory = {//Tells the Pager where to get the news from.
                NewsPagingSource(//Creates a new NewsPagingSource, which makes the API calls page by page.
                    api = api,
                    filter = filter
                )
            }

        ).flow//Returns a Flow that keeps sending new pages as the user scrolls.

    }
    override fun searchNews(
        query: String
    ): Flow<PagingData<NewsArticle>> {

        return Pager(

            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),

            pagingSourceFactory = {
                SearchPagingSource(
                    api = api,
                    query = query
                )
            }

        ).flow
    }

    /**
     * Maps Network Exceptions
     */
    private fun mapError(
        e: Exception
    ): DomainError {

        return when (e) {

            is UnresolvedAddressException ->
                DomainError.Network

            is SocketTimeoutException ->
                DomainError.Network

            is ConnectException ->
                DomainError.Network

            is IOException ->
                DomainError.Network

            else ->
                DomainError.Unknown(e.message)
        }
    }
}