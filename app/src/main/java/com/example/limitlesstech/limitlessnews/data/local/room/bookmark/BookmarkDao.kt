package com.example.limitlesstech.limitlessnews.data.local.room.bookmark

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(
        article: BookmarkEntity
    )

    @Query("DELETE FROM bookmarks WHERE id = :id")
    suspend fun deleteBookmarkById(
        id: String
    )

    @Query("SELECT * FROM bookmarks")
    fun getBookmarks():
            Flow<List<BookmarkEntity>>

    @Query("SELECT * FROM bookmarks WHERE id = :id")
    fun getBookmarkById(
        id: String
    ): Flow<BookmarkEntity?>

    @Query(
        "SELECT EXISTS(SELECT 1 FROM bookmarks WHERE id = :id)"
    )
    fun isBookmarked(
        id: String
    ): Flow<Boolean>
}