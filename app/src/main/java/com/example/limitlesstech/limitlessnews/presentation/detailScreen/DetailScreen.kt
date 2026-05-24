package com.example.limitlesstech.limitlessnews.presentation.detailScreen

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.limitlesstech.limitlessnews.presentation.detailScreen.components.BottomActions
import com.example.limitlesstech.limitlessnews.presentation.detailScreen.components.DetailContent
import com.example.limitlesstech.limitlessnews.presentation.detailScreen.components.DetailTopBar
import com.example.limitlesstech.limitlessnews.presentation.home.HomeViewModel
import androidx.compose.foundation.layout.Box

@Composable
fun DetailScreen(
    articleId: String,
    viewModel: HomeViewModel,
    navController: NavHostController
) {

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(

        topBar = {

            DetailTopBar(

                onBack = {
                    navController.popBackStack()
                }
            )
        }

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
        }

        else {

            // 🔥 Find article
            val article = uiState.news.firstOrNull {
                it.id == articleId
            }

            // 🔥 Error
            if (uiState.error != null) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),

                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = uiState.error.toString()
                    )
                }
            }

            // 🔥 Article not found
            else if (article == null) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),

                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "Article not found"
                    )
                }
            }

            // 🔥 Success
            else {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {

                    // 🔥 Detail Content
                    DetailContent(
                        article = article,
                        modifier = Modifier.weight(1f)
                    )

                    // 🔥 Bottom Actions
                    BottomActions()
                }
            }
        }
    }
}