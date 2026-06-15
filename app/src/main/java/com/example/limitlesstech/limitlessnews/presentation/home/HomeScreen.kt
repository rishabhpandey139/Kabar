package com.example.limitlesstech.limitlessnews.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.limitlesstech.limitlessnews.domain.common.DomainError
import com.example.limitlesstech.limitlessnews.presentation.common.components.MainBottomBar
import com.example.limitlesstech.limitlessnews.presentation.home.components.HeaderSection
import com.example.limitlesstech.limitlessnews.presentation.home.components.NewsItem
import com.example.limitlesstech.limitlessnews.presentation.home.components.SearchBar
import com.example.limitlesstech.limitlessnews.presentation.home.components.SectionTitle
import com.example.limitlesstech.limitlessnews.presentation.home.components.TrendingCard
import com.example.limitlesstech.limitlessnews.presentation.home.shimmer.HomeShimmer
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel
) {

    val state by homeViewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = {
            MainBottomBar(
                selectedRoute = Routes.Home,
                navController = navController
            )
        }
    ) { padding ->

        when {

            state.isLoading -> {

                HomeShimmer(
                    modifier = Modifier.padding(padding)
                )
            }

            state.error != null -> {

                val message = when (state.error) {

                    DomainError.Network ->
                        "No Internet Connection"

                    is DomainError.Unknown ->
                        state.error.toString()

                    else ->
                        "Something went wrong"
                }

                Text(
                    text = message,
                    modifier = Modifier.padding(padding),
                    color = MaterialTheme.colorScheme.error
                )
            }

            else -> {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {

                    item {
                        HeaderSection()
                    }

                    item {
                        SearchBar()
                    }

                    item {
                        SectionTitle("Latest")
                    }

                    item {
                        state.news.firstOrNull()?.let { article ->
                            TrendingCard(article)
                        }
                    }

                    items(
                        items = state.news,
                        key = { it.id }
                    ) { article ->

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
}