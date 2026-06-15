package com.example.limitlesstech.limitlessnews.domain.usecase.bookmark

import com.example.limitlesstech.limitlessnews.domain.repository.BookmarkRepository
import javax.inject.Inject

class IsBookmarkedUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {

    operator fun invoke(id: String) =
        repository.isBookmarked(id)
}