package com.example.limitlesstech.limitlessnews.data.repositoryImpl

import com.example.limitlesstech.limitlessnews.data.local.room.bookmark.BookmarkDao
import com.example.limitlesstech.limitlessnews.data.local.room.mapper.toBookmarkEntity
import com.example.limitlesstech.limitlessnews.data.local.room.mapper.toNewsArticle
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.domain.repository.BookmarkRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val dao: BookmarkDao,
    private val firebaseAuth: FirebaseAuth
) : BookmarkRepository {

    private fun currentUserId(): String? {
        return firebaseAuth.currentUser?.uid
    }

    override suspend fun toggleBookmark(
        article: NewsArticle
    ) {

        val userId =
            currentUserId()
                ?: return

        val isSaved =
            dao.isBookmarked(
                id = article.id,
                userId = userId
            ).first()

        if (isSaved) {

            dao.deleteBookmarkById(
                id = article.id,
                userId = userId
            )

        } else {

            dao.insertBookmark(
                article.toBookmarkEntity(
                    userId = userId
                )
            )
        }
    }

    override fun isBookmarked(
        id: String
    ): Flow<Boolean> {

        val userId =
            currentUserId()
                ?: return flowOf(false)

        return dao.isBookmarked(
            id = id,
            userId = userId
        )
    }

    override fun getBookmarkedArticleById(
        id: String
    ): Flow<NewsArticle?> {

        val userId =
            currentUserId()
                ?: return flowOf(null)

        return dao.getBookmarkById(
            id = id,
            userId = userId
        ).map { entity ->

            entity?.toNewsArticle()
        }
    }

    override fun getBookmarkedArticles():
            Flow<List<NewsArticle>> {

        val userId =
            currentUserId()
                ?: return flowOf(emptyList())

        return dao.getBookmarks(
            userId = userId
        ).map { list ->

            list.map { entity ->
                entity.toNewsArticle()
            }
        }
    }
}