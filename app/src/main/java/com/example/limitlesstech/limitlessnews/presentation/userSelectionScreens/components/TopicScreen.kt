package com.example.limitlesstech.limitlessnews.presentation.userSelectionScreens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes
import com.example.limitlesstech.limitlessnews.presentation.userSelectionScreens.SelectionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicScreen(
    navController: NavController,
    viewModel: SelectionViewModel
) {

    val state by viewModel.uiState.collectAsState()

    // Only NewsAPI supported categories
    val topics = remember {
        listOf(
            "General",
            "Business",
            "Entertainment",
            "Health",
            "Science",
            "Sports",
            "Technology"
        )
    }

    val filteredTopics = remember(
        state.searchQuery,
        topics
    ) {

        val query = state.searchQuery.trim()

        if (query.isEmpty()) {
            topics
        } else {
            topics.filter {
                it.contains(
                    query,
                    ignoreCase = true
                )
            }
        }
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text(
                        text = "Choose your Topics"
                    )
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            imageVector =
                                Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },

        bottomBar = {

            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .systemBarsPadding()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 12.dp
                    ),

                contentAlignment = Alignment.Center

            ) {

                Button(

                    onClick = {
                        navController.navigate(
                            Routes.Source
                        )
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),

                    shape = RoundedCornerShape(4.dp)

                ) {

                    Text(
                        text = "Next"
                    )
                }
            }
        }

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )

        ) {

            OutlinedTextField(

                value = state.searchQuery,

                onValueChange =
                    viewModel::setSearchQuery,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),

                leadingIcon = {

                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },

                placeholder = {

                    Text(
                        text = "Search"
                    )
                },

                singleLine = true,

                shape = RoundedCornerShape(4.dp),

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor =
                        MaterialTheme.colorScheme.primary,

                    unfocusedBorderColor =
                        MaterialTheme.colorScheme.outline
                )
            )

            LazyVerticalGrid(

                columns = GridCells.Fixed(3),

                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),

                contentPadding = PaddingValues(
                    bottom = 16.dp
                ),

                horizontalArrangement =
                    Arrangement.spacedBy(8.dp),

                verticalArrangement =
                    Arrangement.spacedBy(8.dp)

            ) {

                items(
                    items = filteredTopics,
                    key = { it }
                ) { topic ->

                    TopicItem(

                        title = topic,

                        isSelected =
                            state.topic.equals(
                                topic,
                                ignoreCase = true
                            ),

                        onClick = {

                            viewModel.setTopic(
                                topic.lowercase()
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun TopicItem(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val shape = RoundedCornerShape(4.dp)

    Box(

        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .background(
                color =
                    if (isSelected) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.surface
                    },
                shape = shape
            )
            .border(
                width =
                    if (isSelected) 0.dp else 1.dp,

                color =
                    MaterialTheme.colorScheme.primary,

                shape = shape
            )
            .clickable(
                onClick = onClick
            ),

        contentAlignment = Alignment.Center

    ) {

        Text(

            text = title,

            modifier = Modifier.padding(
                horizontal = 4.dp
            ),

            textAlign = TextAlign.Center,

            style =
                MaterialTheme.typography.labelMedium,

            color =
                if (isSelected) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.primary
                }
        )
    }
}