package com.example.limitlesstech.limitlessnews.presentation.detailScreen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.limitlesstech.limitlessnews.di.SelectedArticleEntryPoint
import com.example.limitlesstech.limitlessnews.presentation.bookmark.BookmarkViewModel
import com.example.limitlesstech.limitlessnews.presentation.detailScreen.components.BottomActions
import com.example.limitlesstech.limitlessnews.presentation.detailScreen.components.DetailContent
import com.example.limitlesstech.limitlessnews.presentation.detailScreen.components.DetailTopBar
import dagger.hilt.android.EntryPointAccessors

@Composable
fun DetailScreen(
    articleId: String,
    navController: NavHostController,
    bookmarkViewModel: BookmarkViewModel
) {

    val context = LocalContext.current

    val detailViewModel: DetailViewModel = hiltViewModel()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val selectedArticleManager = remember {//proper hilt implemented later
        EntryPointAccessors.fromApplication(
            context.applicationContext,
            SelectedArticleEntryPoint::class.java
        ).selectedArticleManager()
    }

    val bookmarkedArticle by bookmarkViewModel
        .getBookmarkById(articleId)
        .collectAsState(initial = null)

    val article = selectedArticleManager.getArticle()
        ?: bookmarkedArticle

    LaunchedEffect(Unit) {
        detailViewModel.uiEvent.collect { event ->

            when (event) {

                is DetailUiEvent.OpenBrowser -> {

                    context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(event.url)
                        )
                    )
                }

                is DetailUiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
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
                    modifier = Modifier.weight(1f),
                    onReadFullArticleClick = {
                        detailViewModel.onReadFullArticleClicked(
                            articleUrl = article.link,
                            isBookmarked = isBookmarked
                        )
                    }
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