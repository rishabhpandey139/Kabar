package com.example.limitlesstech.limitlessnews.data.local.room.bookmark


import androidx.room.Database
import androidx.room.RoomDatabase
//Ye file Room database banati hai aur Bookmark table ko manage karti hai.
@Database(
    entities = [BookmarkEntity::class],//Room BookmarkEntity se bookmarks table banayega.
    version = 2//Database version 1 hai, agar future me changes karne honge to version badhana padega
)
abstract class NewsDatabase : RoomDatabase() {//Ye main database class hai

    abstract fun bookmarkDao(): BookmarkDao//Database se BookmarkDao access karo. doa use hoga insert, delete, getBookmarks k liye
}