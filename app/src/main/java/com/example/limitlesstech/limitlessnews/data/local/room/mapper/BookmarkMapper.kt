package com.example.limitlesstech.limitlessnews.data.local.room.mapper


import com.example.limitlesstech.limitlessnews.data.local.room.bookmark.BookmarkEntity
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle

fun NewsArticle.toBookmarkEntity(): BookmarkEntity {

    return BookmarkEntity(
        id = id,
        title = title,
        description = description,
        content = content,
        imageUrl = imageUrl,
        source = source,
        date = date,
        link = link
    )
}
fun BookmarkEntity.toNewsArticle(): NewsArticle {

    return NewsArticle(
        id = id,
        title = title,
        content = content,
        imageUrl = imageUrl,
        source = source,
        date = date,
        link = link,
        description = description
    )
}