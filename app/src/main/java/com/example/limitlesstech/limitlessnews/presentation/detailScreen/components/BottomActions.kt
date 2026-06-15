package com.example.limitlesstech.limitlessnews.presentation.detailScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.presentation.bookmark.BookmarkViewModel

@Composable
fun BottomActions(

    article: NewsArticle,

    isBookmarked: Boolean,

    bookmarkViewModel: BookmarkViewModel
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),

        horizontalArrangement =
            Arrangement.SpaceBetween
    ) {

        Row {

            Text("❤️ 24.5K")

            Spacer(
                Modifier.width(16.dp)
            )

            Text("💬 1K")
        }

        IconButton(

            onClick = {

                bookmarkViewModel
                    .toggleBookmark(article)
            }
        ) {

            Icon(

                imageVector =

                    if (isBookmarked)
                        Icons.Filled.Bookmark
                    else
                        Icons.Outlined.BookmarkBorder,

                contentDescription = "Bookmark"
            )
        }
    }
}