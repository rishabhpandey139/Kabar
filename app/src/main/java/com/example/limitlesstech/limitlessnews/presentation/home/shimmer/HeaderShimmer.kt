package com.example.limitlesstech.limitlessnews.presentation.home.shimmer

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HeaderShimmer() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        ShimmerBox(
            modifier = Modifier
                .width(120.dp)
                .height(32.dp)
        )

        ShimmerBox(
            modifier = Modifier.size(40.dp)
        )
    }
}