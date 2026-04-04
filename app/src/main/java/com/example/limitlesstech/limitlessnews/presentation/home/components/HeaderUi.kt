package com.example.limitlesstech.limitlessnews.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.example.limitlesstech.limitlessnews.R

@Composable
fun HeaderSection() {

    Box(
        modifier = Modifier
            .fillMaxWidth()

    ) {

        val baseSize = 360.dp // 👈 stable reference (better than maxWidth)

        // 🔥 Responsive values
        val padding = baseSize * 0.04f
        val logoHeight = baseSize * 0.08f
        val iconSize = baseSize * 0.06f
        val bgSize = baseSize * 0.12f

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = padding,
                    end = padding,
                    bottom = padding
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // ✅ Logo
            Image(
                painter = painterResource(id = R.drawable.kabaricon),
                contentDescription = "Logo",
                modifier = Modifier.height(logoHeight)
            )

            // 🔔 Notification Icon
            IconButton(onClick = { }) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(bgSize)
                        .background(Color.White, shape = CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.notification_icon),
                        contentDescription = "Notifications",
                        modifier = Modifier.size(iconSize)
                    )
                }
            }
        }
    }
}