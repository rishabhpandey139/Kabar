// app/src/main/java/com/example/limitlesstech/limitlessnews/presentation/detailScreen/DetailScreen.kt
package com.example.limitlesstech.limitlessnews.presentation.detailScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.limitlesstech.limitlessnews.core.util.Result
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
        when (val news = uiState.news) {
            is Result.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is Result.Success -> {
                val article = news.data.firstOrNull { it.id == articleId }
                if (article == null) {
                    Text(modifier = Modifier.padding(padding), text = "Article not found")
                } else {
                    Text(
                        modifier = Modifier.padding(padding),
                        text = article.title
                    )
                }
            }

            is Result.Failure -> {
                Text(modifier = Modifier.padding(padding), text = news.message)
            }

            else -> Unit
        }
    }
}