package com.example.limitlesstech.limitlessnews.presentation.home.shimmer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeShimmer(
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {

        item {
            HeaderShimmer()
        }

        item {
            SearchBarShimmer()
        }

        item {
            TrendingCardShimmer()
        }

        items(8) {
            NewsItemShimmer()
        }

        item {
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}