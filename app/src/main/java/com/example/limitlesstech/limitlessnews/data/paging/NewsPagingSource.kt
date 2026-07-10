package com.example.limitlesstech.limitlessnews.data.paging




import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.limitlesstech.limitlessnews.core.network.NewsApi
import com.example.limitlesstech.limitlessnews.data.mapper.toDomain
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.domain.model.NewsFilter
import javax.inject.Inject

class NewsPagingSource @Inject constructor(
    private val api: NewsApi,
    private val filter: NewsFilter
) : PagingSource<Int, NewsArticle>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, NewsArticle> {
        val page = params.key ?: 1
        return try {


            Log.d("Paging", "Loading page = $page")

            val sourcesParam = filter.sources
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .joinToString(",")

            val response = api.getPagedNews(

                category = filter.category,
                sources = sourcesParam.ifBlank { null },
                page = page,
                pageSize = params.loadSize
            )
            Log.d(
                "Paging3",
                "Page $page loaded successfully. Articles = ${response.articles.size}"
            )

            val articles = response.articles.map { it.toDomain() }
            Log.d(
                "Paging",
                "Loaded ${articles.size} articles for page $page"
            )


            LoadResult.Page(
                data = articles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (articles.isEmpty()) {
                    null
                } else {
                    page + 1
                }
            )

        } catch (e: Exception) {
            Log.e(
                "Paging3",
                "Failed page = $page",
                e
            )
            LoadResult.Error(e)

        }
    }
//Decides which page should reload when the user refreshes or after a configuration change (like screen rotation).
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