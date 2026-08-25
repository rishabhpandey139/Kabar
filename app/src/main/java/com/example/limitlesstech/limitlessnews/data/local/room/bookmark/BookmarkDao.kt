package com.example.limitlesstech.limitlessnews.data.local.room.bookmark

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertBookmark(
        article: BookmarkEntity
    )

    @Query(
        """
        DELETE FROM bookmarks
        WHERE id = :id
        AND userId = :userId
        """
    )
    suspend fun deleteBookmarkById(
        id: String,
        userId: String
    )

    @Query(
        """
        SELECT * FROM bookmarks
        WHERE userId = :userId
        """
    )
    fun getBookmarks(
        userId: String
    ): Flow<List<BookmarkEntity>>

    @Query(
        """
        SELECT * FROM bookmarks
        WHERE id = :id
        AND userId = :userId
        LIMIT 1
        """
    )
    fun getBookmarkById(
        id: String,
        userId: String
    ): Flow<BookmarkEntity?>

    @Query(
        """
        SELECT EXISTS(
            SELECT 1
            FROM bookmarks
            WHERE id = :id
            AND userId = :userId
        )
        """
    )
    fun isBookmarked(
        id: String,
        userId: String
    ): Flow<Boolean>
}