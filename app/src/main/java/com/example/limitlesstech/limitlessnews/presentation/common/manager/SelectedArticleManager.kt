package com.example.limitlesstech.limitlessnews.presentation.common.manager

import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import javax.inject.Inject
import javax.inject.Singleton

//It temporarily stores the selected news article so it can be used on another screen.
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