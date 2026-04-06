package com.example.limitlesstech.limitlessnews.presentation.detailScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.limitlesstech.limitlessnews.core.util.Result
import com.example.limitlesstech.limitlessnews.presentation.detailScreen.components.BottomActions
import com.example.limitlesstech.limitlessnews.presentation.detailScreen.components.DetailContent
import com.example.limitlesstech.limitlessnews.presentation.home.NewsViewModel

@Composable
fun DetailScreen(
    articleId: String,
    viewModel: NewsViewModel
) {

    val state = viewModel.state

    Scaffold(
        topBar = { DetailTopBar() },

        bottomBar = { BottomActions() },


    ) { padding ->

        when (state) {

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

                val article = state.data.find { it.id == articleId }

                if (article == null) {
                    Text("Article not found")
                } else {
                    DetailContent(
                        article = article,
                        modifier = Modifier.padding(padding)
                    )
                }
            }

            is Result.Failure -> {
                Text("Error loading data")
            }

            else -> {}
        }
    }
}