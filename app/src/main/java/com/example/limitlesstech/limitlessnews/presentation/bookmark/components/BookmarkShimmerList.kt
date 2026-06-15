package com.example.limitlesstech.limitlessnews.presentation.bookmark.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BookmarkShimmerList(
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
    ) {

        items(8) {

            BookmarkShimmer()
        }
    }
}