package com.example.limitlesstech.limitlessnews.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.limitlesstech.limitlessnews.R

@Composable
fun HeaderSection(
    profileImageUrl: String = ""
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        val baseSize = 360.dp

        val padding = baseSize * 0.04f
        val logoHeight = baseSize * 0.08f
        val bgSize = baseSize * 0.12f

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = padding,
                    end = padding,
                    bottom = padding
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(
                    id = R.drawable.kabaricon
                ),
                contentDescription = "Logo",
                modifier = Modifier.height(logoHeight)
            )

            Box(
                modifier = Modifier.weight(1f)
            )

            IconButton(
                onClick = { }
            ) {

                AsyncImage(
                    model = profileImageUrl.ifBlank { null },
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(bgSize)
                        .clip(CircleShape)
                        .background(
                            Color.White,
                            CircleShape
                        ),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}