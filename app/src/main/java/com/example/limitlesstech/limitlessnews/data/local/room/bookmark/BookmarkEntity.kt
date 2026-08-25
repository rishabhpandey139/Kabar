package com.example.limitlesstech.limitlessnews.data.local.room.bookmark

import androidx.room.Entity

@Entity(
    tableName = "bookmarks",
    primaryKeys = [
        "userId",
        "id"
    ]
)
data class BookmarkEntity(

    val userId: String,

    val id: String,

    val title: String,

    val description: String,

    val content: String,

    val imageUrl: String,

    val source: String,

    val date: String,

    val link: String
)