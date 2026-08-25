package com.example.limitlesstech.limitlessnews.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.limitlesstech.limitlessnews.data.local.room.bookmark.BookmarkDao
import com.example.limitlesstech.limitlessnews.data.local.room.bookmark.BookmarkEntity

@Database(
    entities = [
        BookmarkEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun bookmarkDao(): BookmarkDao
}