package com.example.limitlesstech.limitlessnews.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding() // 🔥 system nav handle
            .padding(horizontal = 16.dp)// 👈 floating effect
    ) {

        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50.dp)), // 🔥 rounded corners



        ) {

            NavigationBarItem(
                selected = true,
                onClick = {},
                icon = { Icon(Icons.Default.Home, contentDescription = null) },
                label = { Text("Home") }
            )

            NavigationBarItem(
                selected = false,
                onClick = {},
                icon = { Icon(Icons.Default.Search, contentDescription = null) },
                label = { Text("Explore") }
            )

            NavigationBarItem(
                selected = false,
                onClick = {},
                icon = { },
                label = { Text("Bookmark") }
            )

            NavigationBarItem(
                selected = false,
                onClick = {},
                icon = { Icon(Icons.Default.Person, contentDescription = null) },
                label = { Text("Profile") }
            )
        }
    }
}