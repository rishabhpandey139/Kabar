package com.example.limitlesstech.limitlessnews.presentation.userSelectionScreens.components



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.limitlesstech.limitlessnews.presentation.common.SelectionViewModel
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes

private data class UiCountry(
    val code: String,
    val name: String,
    val flagEmoji: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryScreen(
    navController: NavController,
    viewModel: SelectionViewModel

) {

    val state by viewModel.uiState.collectAsState()

    val countries = remember {
        listOf(
            UiCountry(code = "is", name = "Iceland", flagEmoji = "\uD83C\uDDEE\uD83C\uDDF8"),
            UiCountry(code = "in", name = "India", flagEmoji = "\uD83C\uDDEE\uD83C\uDDF3"),
            UiCountry(code = "id", name = "Indonesia", flagEmoji = "\uD83C\uDDEE\uD83C\uDDE9"),
            UiCountry(code = "ir", name = "Iran", flagEmoji = "\uD83C\uDDEE\uD83C\uDDF7"),
            UiCountry(code = "iq", name = "Iraq", flagEmoji = "\uD83C\uDDEE\uD83C\uDDF6"),
            UiCountry(code = "ie", name = "Ireland", flagEmoji = "\uD83C\uDDEE\uD83C\uDDEA"),
            UiCountry(code = "il", name = "Israel", flagEmoji = "\uD83C\uDDEE\uD83C\uDDF1"),
            UiCountry(code = "us", name = "United States", flagEmoji = "\uD83C\uDDFA\uD83C\uDDF8"),
            UiCountry(code = "gb", name = "United Kingdom", flagEmoji = "\uD83C\uDDEC\uD83C\uDDE7"),
            UiCountry(code = "au", name = "Australia", flagEmoji = "\uD83C\uDDE6\uD83C\uDDFA")

        )
    }

    val filtered = remember(state.searchQuery) {
        val q = state.searchQuery.trim()
        if (q.isEmpty()) countries else countries.filter { it.name.contains(q, ignoreCase = true) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Select your Country") },
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { navController.navigate(Routes.Topic) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(text = "Next")
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = viewModel::setSearchQuery,
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                placeholder = { Text(text = "Search") },
                singleLine = true
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filtered, key = { it.code }) { c ->
                    val selected = state.country.equals(c.code, ignoreCase = true)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                if (selected) Color(0xFF1677FF) else Color.Transparent
                            )
                            .clickable { viewModel.setCountry(c.code) }
                            .padding(horizontal = 12.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = c.flagEmoji,
                            modifier = Modifier.size(22.dp)
                        )

                        Text(
                            text = c.name,
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (selected) Color.White else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}