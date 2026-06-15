package com.example.limitlesstech.limitlessnews.presentation.detailScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import com.example.limitlesstech.limitlessnews.presentation.bookmark.BookmarkViewModel
import com.example.limitlesstech.limitlessnews.presentation.detailScreen.components.BottomActions
import com.example.limitlesstech.limitlessnews.presentation.detailScreen.components.DetailContent
import com.example.limitlesstech.limitlessnews.presentation.detailScreen.components.DetailTopBar
import com.example.limitlesstech.limitlessnews.presentation.home.HomeViewModel

@Composable
fun DetailScreen(
    articleId: String,
    viewModel: HomeViewModel,
    navController: NavHostController,
    bookmarkViewModel: BookmarkViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    val bookmarkedArticle by bookmarkViewModel
        .getBookmarkById(articleId)
        .collectAsState(initial = null)

    val article =
        uiState.news.firstOrNull {//further improve searching do'n search artilce id from homeviewmodel if user click on bookmark screen
            it.id == articleId
        } ?: bookmarkedArticle

    Scaffold(
        topBar = {
            DetailTopBar(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    ) { padding ->

        if (article == null) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Article not found")
            }

        } else {

            val isBookmarked by bookmarkViewModel
                .isBookmarked(article.id)
                .collectAsState(initial = false)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {

                DetailContent(
                    article = article,
                    modifier = Modifier.weight(1f)
                )

                BottomActions(
                    article = article,
                    isBookmarked = isBookmarked,
                    bookmarkViewModel = bookmarkViewModel
                )
            }
        }
    }
}