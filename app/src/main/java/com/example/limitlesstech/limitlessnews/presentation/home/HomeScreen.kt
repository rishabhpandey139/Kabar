
package com.example.limitlesstech.limitlessnews.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.limitlesstech.limitlessnews.core.util.Result

import com.example.limitlesstech.limitlessnews.presentation.home.components.BottomBar
import com.example.limitlesstech.limitlessnews.presentation.home.components.HeaderSection
import com.example.limitlesstech.limitlessnews.presentation.home.components.NewsItem
import com.example.limitlesstech.limitlessnews.presentation.home.components.SearchBar
import com.example.limitlesstech.limitlessnews.presentation.home.components.SectionTitle
import com.example.limitlesstech.limitlessnews.presentation.home.components.TrendingCard
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: NewsViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.fetchNews("us", "technology")
    }

    val state = viewModel.state

    Scaffold(
        bottomBar = { BottomBar() },
        // remove system insets, we handle status bar manually
        contentWindowInsets = WindowInsets(0)
    ) { padding ->

        when (state) {

            is Result.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is Result.Success -> {
                val articles = state.data

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .padding(padding)
                ) {
                    HeaderSection()
                    SearchBar()

                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        item { SectionTitle("Trending") }

                        item {
                            articles.firstOrNull()?.let {
                                TrendingCard(it)
                            }
                        }

                        item { SectionTitle("Latest") }

                        items(articles) { article ->
                            NewsItem(
                                article = article,
                                onClick = {
                                    navController.navigate(Routes.Details(article.id))
                                }
                            )
                        }
                    }
                }
            }

            is Result.Failure -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Something went wrong")
                }
            }

            else -> {
                // idle or unknown state; render nothing
            }
        }
    }
}
