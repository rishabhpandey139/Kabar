package com.example.limitlesstech.limitlessnews.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

import com.example.limitlesstech.limitlessnews.core.util.Result
import com.example.limitlesstech.limitlessnews.presentation.home.components.*

@Composable
fun HomeScreen(
    viewModel: NewsViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.fetchNews("us", "technology")
    }

    val state = viewModel.state

    Scaffold(
        bottomBar = { BottomBar() },
        contentWindowInsets = WindowInsets(0) // 🔥 full control//remove system padding statusbar and navigation bars
    ) { padding ->

        when (state) {

            is Result.Loading -> {
                Box(

                    modifier = Modifier.fillMaxSize()
                        .background(color = Color.Red), // 🔥 background for loading state
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
                        .statusBarsPadding() // 🔥 only top safe
                        .padding(padding)
                ) {

                    HeaderSection()
                    SearchBar()

                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        item { SectionTitle("Trending") }

                        item {
                            articles.firstOrNull()?.let {
                                TrendingCard(it)
                            }
                        }

                        item { SectionTitle("Latest") }

                        items(articles) { article ->
                            NewsItem(article)
                        }
                    }
                }
            }

            is Result.Failure -> {
                Text(text = "Something went wrong")
            }

            else -> {}
        }
    }
}