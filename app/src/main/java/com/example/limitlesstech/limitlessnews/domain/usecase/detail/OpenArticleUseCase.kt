package com.example.limitlesstech.limitlessnews.domain.usecase.detail

import com.example.limitlesstech.limitlessnews.domain.model.OpenArticleAction
import com.example.limitlesstech.limitlessnews.domain.repository.NetworkRepository
import javax.inject.Inject

class OpenArticleUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {

    operator fun invoke(
        articleUrl: String,
        isBookmarked: Boolean
    ): OpenArticleAction {//below is the decision making logic to determine what action to take based on network availability and bookmark status

        return when {
            networkRepository.isInternetAvailable() ->
                OpenArticleAction.OpenBrowser(articleUrl)

            isBookmarked ->
                OpenArticleAction.ShowOfflineArticle

            else ->
                OpenArticleAction.ShowNoInternetMessage
        }
    }
}