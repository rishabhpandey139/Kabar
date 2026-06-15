package com.example.limitlesstech.limitlessnews.presentation.home.shimmer


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NewsItemShimmer() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        ShimmerBox(
            modifier = Modifier.size(90.dp)
        )

        Spacer(Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            ShimmerBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp)
            )

            Spacer(Modifier.height(8.dp))

            ShimmerBox(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(18.dp)
            )

            Spacer(Modifier.height(12.dp))

            ShimmerBox(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .height(14.dp)
            )
        }
    }
}