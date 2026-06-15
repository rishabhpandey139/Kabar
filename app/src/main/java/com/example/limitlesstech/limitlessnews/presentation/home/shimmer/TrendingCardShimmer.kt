package com.example.limitlesstech.limitlessnews.presentation.home.shimmer

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TrendingCardShimmer() {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        ShimmerBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )

        Spacer(Modifier.height(12.dp))

        ShimmerBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )

        Spacer(Modifier.height(8.dp))

        ShimmerBox(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(16.dp)
        )
    }
}