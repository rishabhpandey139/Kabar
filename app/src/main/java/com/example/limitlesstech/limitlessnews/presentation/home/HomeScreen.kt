package com.example.limitlesstech.limitlessnews.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.limitlesstech.limitlessnews.domain.model.NewsFilter
import com.example.limitlesstech.limitlessnews.presentation.common.SelectionViewModel
import com.example.limitlesstech.limitlessnews.presentation.home.components.*
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    val parentEntry = remember(navController) {
        navController.getBackStackEntry(Routes.UserSelection)
    }
    val selectionVm: SelectionViewModel = hiltViewModel(parentEntry)
    val selectionState by selectionVm.uiState.collectAsState()

    val state by homeViewModel.uiState.collectAsState()

    // 🔥 Load news
    LaunchedEffect(Unit) {
        homeViewModel.loadNews(
            NewsFilter(
                country = selectionState.country,
                category = selectionState.topic,
                sources = selectionState.sources
            )
        )
    }

    Scaffold(
        bottomBar = { BottomBar() }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            item { HeaderSection() }
            item { Spacer(Modifier.height(8.dp)) }
            item { SearchBar() }
            item { SectionTitle("Latest") }

            // 🔥 LOADING
            if (state.isLoading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            // 🔥 ERROR
            state.error?.let {
                item {
                    Text(
                        text = it.toString(),
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            // 🔥 DATA
            if (state.news.isNotEmpty()) {

                // Trending (first item)
                item {
                    state.news.firstOrNull()?.let {
                        TrendingCard(it)
                    }
                }

                // List
                items(state.news) { article ->
                    NewsItem(
                        article = article,
                        onClick = {
                            navController.navigate(
                                Routes.Details(article.id)
                            )
                        }
                    )
                }
            }
        }
    }
}