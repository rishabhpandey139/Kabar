package com.example.limitlesstech.limitlessnews.presentation.home.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmerBox(
    modifier: Modifier
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                Color.LightGray.copy(alpha = 0.4f)
            )
            .shimmer()
    )
}