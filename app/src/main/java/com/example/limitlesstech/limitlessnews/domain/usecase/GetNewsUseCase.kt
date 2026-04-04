package com.example.limitlesstech.limitlessnews.domain.usecase

import com.example.limitlesstech.limitlessnews.core.util.Result
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.domain.repository.NewsRepository

import javax.inject.Inject


class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(country: String, category: String) : Result<List<NewsArticle>>{
        return repository.getNews(country, category)
    }

}