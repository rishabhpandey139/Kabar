package com.example.limitlesstech.limitlessnews.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.limitlesstech.limitlessnews.di.SelectedArticleEntryPoint
import com.example.limitlesstech.limitlessnews.domain.common.DomainError
import com.example.limitlesstech.limitlessnews.presentation.common.components.MainBottomBar
import com.example.limitlesstech.limitlessnews.presentation.home.components.HeaderSection
import com.example.limitlesstech.limitlessnews.presentation.home.components.NewsItem
import com.example.limitlesstech.limitlessnews.presentation.home.components.SearchBar
import com.example.limitlesstech.limitlessnews.presentation.home.components.SectionTitle
import com.example.limitlesstech.limitlessnews.presentation.home.components.TrendingCard
import com.example.limitlesstech.limitlessnews.presentation.home.shimmer.HomeShimmer
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes
import com.example.limitlesstech.limitlessnews.presentation.search.SearchViewModel
import dagger.hilt.android.EntryPointAccessors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    searchViewModel: SearchViewModel=hiltViewModel()
) {

    val state by homeViewModel.uiState.collectAsState()
    val searchState by searchViewModel.uiState.collectAsState()

    val context = LocalContext.current

    val selectedArticleManager = remember {

        EntryPointAccessors.fromApplication(
            context.applicationContext,
            SelectedArticleEntryPoint::class.java
        ).selectedArticleManager()

    }

    val pagingItems =
        homeViewModel
            .pagedNews
            .collectAsState()
            .value
            .collectAsLazyPagingItems()
    val searchPagingItems =
        searchViewModel
            .searchResults
            .collectAsLazyPagingItems()

    val currentPagingItems =
        if (searchState.query.isBlank()) {
            pagingItems
        } else {
            searchPagingItems
        }


    val pullToRefreshState = rememberPullToRefreshState()

    Scaffold(

        bottomBar = {
            MainBottomBar(
                selectedRoute = Routes.Home,
                navController = navController
            )
        }
    ) { padding ->

        when {
                //home loading
            state.isLoading && searchState.query.isBlank() -> {

                HomeShimmer(
                    modifier = Modifier.padding(padding)
                )
            }
                //home error
            state.error != null && searchState.query.isBlank() -> { //home error ui

                val message = when (state.error) {

                    DomainError.Network ->
                        "No Internet Connection"

                    is DomainError.Unknown ->
                        state.error.toString()

                    else ->
                        "Something went wrong"
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = message,
                        color = MaterialTheme.colorScheme.error
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Button(
                        onClick = {

                            pagingItems.refresh()
                            homeViewModel.refresh()

                        }
                    ) {
                        Text("Retry")
                    }
                }
            }
            // 3. Search Error
            searchState.query.isNotBlank() &&
                    searchPagingItems.loadState.refresh is LoadState.Error -> {
                        val error = searchPagingItems.loadState.refresh as LoadState.Error

                val message = when (error.error) {

                    is java.io.IOException,
                    is java.net.SocketTimeoutException,
                    is java.net.ConnectException,
                    is io.ktor.util.network.UnresolvedAddressException ->
                        "No Internet Connection"

                    else ->
                        "Search failed"
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Search failed",
                        color = MaterialTheme.colorScheme.error
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Button(
                        onClick = {
                            searchPagingItems.refresh()
                        }
                    ) {
                        Text("Retry")
                    }
                }
            }
            else -> {


//                PullToRefreshBox(
//                    state = pullToRefreshState,
//                    isRefreshing = pagingItems.loadState.refresh is LoadState.Loading,
//                    onRefresh = {
//                        pagingItems.refresh()
//                        homeViewModel.refresh()
//                    },
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(padding)
//                )


                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                            .padding(padding)
                    ) {
                        item {
                        HeaderSection()
                    }

                        item {
                            SearchBar(
                                query = searchState.query,
                                onQueryChange = searchViewModel::onQueryChange
                            )
                        }
                        if (searchState.query.isBlank()) {

                            item {

                                state.trendingArticle?.let {

                                    TrendingCard(
                                        article = it,
                                        onClick = {
                                            selectedArticleManager.setArticle(it)

                                            navController.navigate(
                                                Routes.Details(it.id)
                                            )
                                        }
                                    )
                                }
                            }
                        }


                        item {
                            SectionTitle(
                                if (searchState.query.isBlank())
                                    "Latest"
                                else
                                    "Search Results"
                            )
                        }

                        items(
                            count = currentPagingItems.itemCount
                        ) { index ->

                            val article = currentPagingItems[index]

                            if (article != null) {

                                NewsItem(
                                    article = article,
                                    onClick = {

                                        selectedArticleManager.setArticle(article)

                                        navController.navigate(
                                            Routes.Details(article.id)
                                        )
                                    }
                                )
                            }
                        }

                        when (currentPagingItems.loadState.append) {

                            is LoadState.Loading -> {

                                item {

                                    CircularProgressIndicator(
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            }

                            is LoadState.Error -> {

                                item {

                                    Text(
                                        text = "Failed to load more news",
                                        modifier = Modifier.padding(16.dp),
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            }

                            else -> Unit
                        }                    }
                }
            }
        }
    }
