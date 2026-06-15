package com.example.limitlesstech.limitlessnews.presentation.bookmark.components
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun EmptyBookmarkState(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize(),

        contentAlignment =
            Alignment.Center
    ) {

        Text(
            text = "No bookmarks yet"
        )
    }
}