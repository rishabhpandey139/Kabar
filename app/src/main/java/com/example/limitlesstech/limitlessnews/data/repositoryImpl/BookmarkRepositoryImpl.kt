package com.example.limitlesstech.limitlessnews.data.repositoryImpl


import com.example.limitlesstech.limitlessnews.data.local.room.bookmark.BookmarkDao
import com.example.limitlesstech.limitlessnews.data.local.room.mapper.toBookmarkEntity
import com.example.limitlesstech.limitlessnews.data.local.room.mapper.toNewsArticle
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle

import com.example.limitlesstech.limitlessnews.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


//Ye file bookmark add, remove aur check karne ka logic handle karti hai.

class BookmarkRepositoryImpl @Inject constructor(
    private val dao: BookmarkDao
) : BookmarkRepository {

    override suspend fun toggleBookmark(
        article: NewsArticle
    ) {

        val isSaved =
            dao.isBookmarked(article.id).first()//check article is already bookmarked or not

        if (isSaved) {

            dao.deleteBookmarkById(article.id)//Bookmark remove karo

        } else {//nhi h toh bookmark add karo

            dao.insertBookmark(
                article.toBookmarkEntity()//NewsArticle ko database format me convert karo
            )
        }
    }

    override fun isBookmarked(
        id: String
    ): Flow<Boolean> {

        return dao.isBookmarked(id)
    }//UI ko batao bookmarked hai ya nahi



    override fun getBookmarkedArticleById(
        id: String
    ): Flow<NewsArticle?> {

        return dao.getBookmarkById(id).map { it?.toNewsArticle()}


        }

    override fun getBookmarkedArticles():
            Flow<List<NewsArticle>> {

        return dao.getBookmarks().map { list ->

            list.map {
                it.toNewsArticle()
            }
        }
    }
    }
