package com.example.limitlesstech.limitlessnews.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.limitlesstech.limitlessnews.core.util.Result
import com.example.limitlesstech.limitlessnews.domain.model.NewsFilter
import com.example.limitlesstech.limitlessnews.presentation.common.SelectionViewModel
import com.example.limitlesstech.limitlessnews.presentation.home.components.*
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    // 🔥 GET FILTER DATA
    val parentEntry = remember(navController) {
        navController.getBackStackEntry(Routes.UserSelection)
    }
    val selectionVm: SelectionViewModel = hiltViewModel(parentEntry)
    val selectionState by selectionVm.uiState.collectAsState()

    val state by homeViewModel.uiState.collectAsState()

    // 🔥 AUTO LOAD NEWS
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

            when (val result = state.news) {

                is Result.Loading -> {
                    item { CircularProgressIndicator() }
                }

                is Result.Failure -> {
                    item { Text(result.message) }
                }

                is Result.Success -> {

                    // 🔥 TRENDING (FIRST ITEM)
                    item {
                        result.data.firstOrNull()?.let {
                            TrendingCard(it)
                        }
                    }

                    // 🔥 LIST
                    items(result.data) { article ->
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

                else -> Unit
            }
        }
    }
}