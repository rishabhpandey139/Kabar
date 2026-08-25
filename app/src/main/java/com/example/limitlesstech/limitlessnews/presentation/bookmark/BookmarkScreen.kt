package com.example.limitlesstech.limitlessnews.presentation.bookmark

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.limitlesstech.limitlessnews.di.SelectedArticleEntryPoint
import com.example.limitlesstech.limitlessnews.presentation.bookmark.components.BookmarkList
import com.example.limitlesstech.limitlessnews.presentation.bookmark.components.BookmarkSearchBar
import com.example.limitlesstech.limitlessnews.presentation.bookmark.components.BookmarkShimmerList
import com.example.limitlesstech.limitlessnews.presentation.bookmark.components.EmptyBookmarkState
import com.example.limitlesstech.limitlessnews.presentation.common.components.MainBottomBar
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes
import dagger.hilt.android.EntryPointAccessors

@Composable
fun BookmarkScreen(
    navController: NavHostController,
    viewModel: BookmarkViewModel,
    onProfileClick: () -> Unit
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
                navController = navController,
                onProfileClick = onProfileClick
            )
        }

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),

            verticalArrangement =
                Arrangement.Top
        ) {

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(

                text = "Bookmark",

                style =
                    MaterialTheme
                        .typography
                        .headlineMedium
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            BookmarkSearchBar(

                query = state.searchQuery,

                onQueryChange =
                    viewModel::onSearchQueryChange,

                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            when {

                state.isLoading -> {

                    BookmarkShimmerList(
                        modifier = Modifier.fillMaxSize()
                    )
                }

                state.bookmarks.isEmpty() -> {

                    EmptyBookmarkState(
                        modifier = Modifier.fillMaxSize()
                    )
                }

                else -> {

                    BookmarkList(

                        modifier = Modifier
                            .fillMaxSize(),

                        articles =
                            state.filteredBookmarks,

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
}