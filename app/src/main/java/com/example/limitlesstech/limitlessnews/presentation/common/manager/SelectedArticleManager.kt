package com.example.limitlesstech.limitlessnews.presentation.common.manager

import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SelectedArticleManager @Inject constructor() {

    private var selectedArticle: NewsArticle? = null

    fun setArticle(article: NewsArticle) {
        selectedArticle = article
    }

    fun getArticle(): NewsArticle? {
        return selectedArticle
    }

    fun clear() {
        selectedArticle = null
    }
}