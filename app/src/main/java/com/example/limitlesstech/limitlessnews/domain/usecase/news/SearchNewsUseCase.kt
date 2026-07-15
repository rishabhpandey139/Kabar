package com.example.limitlesstech.limitlessnews.domain.usecase.news

import androidx.paging.PagingData
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(
        query: String
    ): Flow<PagingData<NewsArticle>> {

        val validatedQuery = query
            .trim()
            .take(100)

        if (validatedQuery.isBlank()) {
            return emptyFlow()
        }

        return repository.searchNews(validatedQuery)
    }
}