package com.example.limitlesstech.limitlessnews.presentation.detailScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.limitlesstech.limitlessnews.presentation.detailScreen.components.DetailTopBar
import com.example.limitlesstech.limitlessnews.presentation.home.HomeViewModel

@Composable
fun DetailScreen(
    articleId: String,
    viewModel: HomeViewModel,
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { DetailTopBar(onBack = { navController.popBackStack() }) }
    ) { padding ->

        // 🔥 Loading
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        // 🔥 Error
        uiState.error?.let {
            Text(
                modifier = Modifier.padding(padding),
                text = it.toString()
            )
            return@Scaffold
        }

        // 🔥 Data
        val article = uiState.news.firstOrNull { it.id == articleId }

        if (article == null) {
            Text(
                modifier = Modifier.padding(padding),
                text = "Article not found"
            )
        } else {
            Text(
                modifier = Modifier.padding(padding),
                text = article.title
            )
        }
    }
}