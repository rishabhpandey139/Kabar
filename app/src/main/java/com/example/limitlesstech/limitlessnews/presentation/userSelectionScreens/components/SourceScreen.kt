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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes
import com.example.limitlesstech.limitlessnews.presentation.userSelectionScreens.SelectionViewModel

private data class UiSource(
    val id: String,
    val name: String,
    val logoUrl: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SourceScreen(
    navController: NavController,
    viewModel: SelectionViewModel
) {

    val state by viewModel.uiState.collectAsState()

    /*
     * NewsAPI supported source IDs.
     */
    val sources = remember {
        listOf(
            UiSource(
                id = "cnbc",
                name = "CNBC",
                logoUrl = "https://www.google.com/s2/favicons?domain=cnbc.com&sz=128"
            ),

            UiSource(
                id = "vice-news",
                name = "VICE",
                logoUrl = "https://www.google.com/s2/favicons?domain=vice.com&sz=128"
            ),

            UiSource(
                id = "vox",
                name = "Vox",
                logoUrl = "https://www.google.com/s2/favicons?domain=vox.com&sz=128"
            ),

            UiSource(
                id = "bbc-news",
                name = "BBC News",
                logoUrl = "https://www.google.com/s2/favicons?domain=bbc.com&sz=128"
            ),

            UiSource(
                id = "cnn",
                name = "CNN",
                logoUrl = "https://www.google.com/s2/favicons?domain=cnn.com&sz=128"
            ),

            UiSource(
                id = "usa-today",
                name = "USA Today",
                logoUrl = "https://www.google.com/s2/favicons?domain=usatoday.com&sz=128"
            ),

            UiSource(
                id = "time",
                name = "TIME",
                logoUrl = "https://www.google.com/s2/favicons?domain=time.com&sz=128"
            ),

            UiSource(
                id = "cnet",
                name = "CNET",
                logoUrl = "https://www.google.com/s2/favicons?domain=cnet.com&sz=128"
            ),

            UiSource(
                id = "msnbc",
                name = "MSNBC",
                logoUrl = "https://www.google.com/s2/favicons?domain=msnbc.com&sz=128"
            ),

            UiSource(
                id = "techcrunch",
                name = "TechCrunch",
                logoUrl = "https://www.google.com/s2/favicons?domain=techcrunch.com&sz=128"
            ),

            UiSource(
                id = "the-verge",
                name = "The Verge",
                logoUrl = "https://www.google.com/s2/favicons?domain=theverge.com&sz=128"
            ),

            UiSource(
                id = "the-next-web",
                name = "The Next Web",
                logoUrl = "https://www.google.com/s2/favicons?domain=thenextweb.com&sz=128"
            )

        )
    }

    val filteredSources = remember(
        state.searchQuery,
        sources
    ) {

        val query = state.searchQuery.trim()

        if (query.isEmpty()) {
            sources
        } else {
            sources.filter {
                it.name.contains(
                    other = query,
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
                        text = "Choose your News Sources"
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
                            Routes.Home
                        ) {

                            popUpTo(
                                Routes.UserSelection
                            ) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
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
                    .padding(top = 12.dp),

                contentPadding = PaddingValues(
                    bottom = 16.dp
                ),

                horizontalArrangement =
                    Arrangement.spacedBy(10.dp),

                verticalArrangement =
                    Arrangement.spacedBy(10.dp)

            ) {

                items(
                    items = filteredSources,
                    key = { it.id }
                ) { source ->

                    val isFollowing =
                        state.sources.contains(source.id)

                    SourceItem(

                        source = source,

                        isFollowing = isFollowing,

                        onClick = {

                            viewModel.toggleSource(
                                source.id
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun SourceItem(
    source: UiSource,
    isFollowing: Boolean,
    onClick: () -> Unit
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .height(142.dp),

        shape = RoundedCornerShape(8.dp),

        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.surfaceVariant
        )

    ) {

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 8.dp,
                    vertical = 10.dp
                ),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.SpaceBetween

        ) {

            AsyncImage(
                model = source.logoUrl,
                contentDescription = source.name,
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(26.dp)),
                contentScale = ContentScale.Fit
            )

            Text(

                text = source.name,

                style =
                    MaterialTheme.typography.labelMedium,

                textAlign = TextAlign.Center,

                maxLines = 2,

                overflow =
                    TextOverflow.Ellipsis
            )

            SourceFollowButton(

                isFollowing = isFollowing,

                onClick = onClick
            )
        }
    }
}

@Composable
private fun SourceFollowButton(
    isFollowing: Boolean,
    onClick: () -> Unit
) {

    val shape = RoundedCornerShape(4.dp)

    Box(

        modifier = Modifier
            .fillMaxWidth()
            .height(28.dp)
            .clip(shape)
            .background(

                color =

                    if (isFollowing) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.surface
                    }
            )
            .border(

                width =
                    if (isFollowing) {
                        0.dp
                    } else {
                        1.dp
                    },

                color =
                    MaterialTheme.colorScheme.primary,

                shape = shape
            )
            .clickable(
                onClick = onClick
            ),

        contentAlignment =
            Alignment.Center

    ) {

        Text(

            text =
                if (isFollowing) {
                    "Following"
                } else {
                    "Follow"
                },

            style =
                MaterialTheme.typography.labelSmall,

            color =
                if (isFollowing) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.primary
                }
        )
    }
}