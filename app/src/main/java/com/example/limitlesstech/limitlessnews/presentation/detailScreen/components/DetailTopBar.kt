package com.example.limitlesstech.limitlessnews.presentation.detailScreen

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar() {
    TopAppBar(modifier = Modifier.statusBarsPadding(),
        title = {},
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.Share, contentDescription = null)
            }
        }
    )
}