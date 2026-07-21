package com.example.limitlesstech.limitlessnews.presentation.bookmark

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.limitlesstech.limitlessnews.di.SelectedArticleEntryPoint
import com.example.limitlesstech.limitlessnews.presentation.bookmark.components.BookmarkList
import com.example.limitlesstech.limitlessnews.presentation.bookmark.components.BookmarkShimmerList
import com.example.limitlesstech.limitlessnews.presentation.bookmark.components.EmptyBookmarkState
import com.example.limitlesstech.limitlessnews.presentation.common.components.MainBottomBar
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes
import dagger.hilt.android.EntryPointAccessors

@Composable
fun BookmarkScreen(
    navController: NavHostController,
    viewModel: BookmarkViewModel
) {

    val state by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    val selectedArticleManager = remember {
        EntryPointAccessors.fromApplication(
            context.applicationContext,
            SelectedArticleEntryPoint::class.java
        ).selectedArticleManager()
    }


    Scaffold(

        modifier = Modifier.fillMaxSize(),

        bottomBar = {

            MainBottomBar(
                selectedRoute = Routes.Bookmark,
                navController = navController
            )
        }

    ) { padding ->

        when {

            state.isLoading -> {

                BookmarkShimmerList(
                    modifier = Modifier.padding(padding)
                )
            }

            state.bookmarks.isEmpty() -> {

                EmptyBookmarkState(
                    modifier = Modifier.padding(padding)
                )
            }

            else -> {

                BookmarkList(

                    modifier = Modifier.padding(padding),

                    articles = state.bookmarks,

                    onArticleClick = { articleId ->
                        selectedArticleManager.clear()


                        navController.navigate(
                            Routes.Details(articleId)
                        )
                    }
                )
            }
        }
    }
}