package com.example.limitlesstech.limitlessnews.domain.usecase.news

import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.domain.model.NewsFilter
import com.example.limitlesstech.limitlessnews.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(filter: NewsFilter): Result<List<NewsArticle>> {
        return repository.getNews(filter)
    }
}