package com.example.limitlesstech.limitlessnews.domain.usecase.bookmark

import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.domain.repository.BookmarkRepository
import javax.inject.Inject

//Bookmark add/remove karne ka use case
class ToggleBookmarkUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {

    suspend operator fun invoke(
        article: NewsArticle
    ) {

        repository.toggleBookmark(article)//Repository ko bolo bookmark ON/OFF karo
    }
}