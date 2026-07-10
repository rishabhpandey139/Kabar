package com.example.limitlesstech.limitlessnews.domain.usecase.news

import androidx.paging.PagingData
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.domain.model.NewsFilter
import com.example.limitlesstech.limitlessnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagedNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(
        filter: NewsFilter
    ): Flow<PagingData<NewsArticle>> {

        return repository.getPagedNews(filter)

    }
}