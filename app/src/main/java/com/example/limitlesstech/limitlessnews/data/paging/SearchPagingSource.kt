package com.example.limitlesstech.limitlessnews.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.limitlesstech.limitlessnews.core.network.NewsApi
import com.example.limitlesstech.limitlessnews.data.mapper.toDomain
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import javax.inject.Inject

class SearchPagingSource @Inject constructor(//Used for Search results
    private val api: NewsApi,
    private val query: String
) : PagingSource<Int, NewsArticle>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, NewsArticle> {

        val page = params.key ?: 1

        return try {

            Log.d("SearchPaging", "Query = $query Page = $page")

            val response = api.searchNews(
                query = query,
                page = page,
                pageSize = params.loadSize
            )

            val articles = response.articles.map { it.toDomain() }

            Log.d(
                "SearchPaging",
                "Loaded ${articles.size} search results"
            )

            LoadResult.Page(
                data = articles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (articles.isEmpty()) null else page + 1
            )

        } catch (e: Exception) {

            Log.e(
                "SearchPaging",
                "Search failed",
                e
            )

            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, NewsArticle>
    ): Int? {

        return state.anchorPosition?.let { position ->

            val anchorPage = state.closestPageToPosition(position)

            anchorPage?.prevKey?.plus(1)
                ?: anchorPage?.nextKey?.minus(1)
        }
    }
}