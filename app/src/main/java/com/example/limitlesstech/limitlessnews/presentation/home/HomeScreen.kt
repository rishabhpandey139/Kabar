// presentation/home/HomeScreen.kt

package com.example.limitlesstech.limitlessnews.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.limitlesstech.limitlessnews.presentation.home.components.BottomBar
import com.example.limitlesstech.limitlessnews.presentation.home.components.HeaderSection
import com.example.limitlesstech.limitlessnews.presentation.home.components.NewsItem
import com.example.limitlesstech.limitlessnews.presentation.home.components.SearchBar
import com.example.limitlesstech.limitlessnews.presentation.home.components.SectionTitle
import com.example.limitlesstech.limitlessnews.presentation.home.components.TrendingCard
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    val state by homeViewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = {
            BottomBar()
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            item {
                HeaderSection()
            }

            item {
                Spacer(Modifier.height(8.dp))
            }

            item {
                SearchBar()
            }

            item {
                SectionTitle("Latest")
            }

            // 🔥 Loading
            if (state.isLoading) {

                item {

                    CircularProgressIndicator(
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            // 🔥 Error
            state.error?.let { error ->

                item {

                    Text(
                        text = error.toString(),

                        modifier = Modifier.padding(16.dp),

                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            // 🔥 News List
            if (state.news.isNotEmpty()) {

                // Trending
                item {

                    state.news.firstOrNull()?.let { article ->


                        TrendingCard(article)
                    }
                }

                // News Items
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