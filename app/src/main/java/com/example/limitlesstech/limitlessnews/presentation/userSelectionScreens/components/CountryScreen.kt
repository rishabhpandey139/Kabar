package com.example.limitlesstech.limitlessnews.presentation.userSelectionScreens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.navigation.NavController
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes
import com.example.limitlesstech.limitlessnews.presentation.userSelectionScreens.SelectionViewModel

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

    /*
     * NewsAPI supported country codes.
     * These countries are supported by NewsAPI's documented
     * country/source list.
     */
    val countries = remember {
        listOf(
            UiCountry("ae", "United Arab Emirates", "🇦🇪"),
            UiCountry("ar", "Argentina", "🇦🇷"),
            UiCountry("at", "Austria", "🇦🇹"),
            UiCountry("au", "Australia", "🇦🇺"),
            UiCountry("be", "Belgium", "🇧🇪"),
            UiCountry("bg", "Bulgaria", "🇧🇬"),
            UiCountry("br", "Brazil", "🇧🇷"),
            UiCountry("ca", "Canada", "🇨🇦"),
            UiCountry("ch", "Switzerland", "🇨🇭"),
            UiCountry("cn", "China", "🇨🇳"),
            UiCountry("co", "Colombia", "🇨🇴"),
            UiCountry("cu", "Cuba", "🇨🇺"),
            UiCountry("cz", "Czech Republic", "🇨🇿"),
            UiCountry("de", "Germany", "🇩🇪"),
            UiCountry("eg", "Egypt", "🇪🇬"),
            UiCountry("fr", "France", "🇫🇷"),
            UiCountry("gb", "United Kingdom", "🇬🇧"),
            UiCountry("gr", "Greece", "🇬🇷"),
            UiCountry("hk", "Hong Kong", "🇭🇰"),
            UiCountry("hu", "Hungary", "🇭🇺"),
            UiCountry("id", "Indonesia", "🇮🇩"),
            UiCountry("ie", "Ireland", "🇮🇪"),
            UiCountry("il", "Israel", "🇮🇱"),
            UiCountry("in", "India", "🇮🇳"),
            UiCountry("it", "Italy", "🇮🇹"),
            UiCountry("jp", "Japan", "🇯🇵"),
            UiCountry("kr", "South Korea", "🇰🇷"),
            UiCountry("lt", "Lithuania", "🇱🇹"),
            UiCountry("lv", "Latvia", "🇱🇻"),
            UiCountry("ma", "Morocco", "🇲🇦"),
            UiCountry("mx", "Mexico", "🇲🇽"),
            UiCountry("my", "Malaysia", "🇲🇾"),
            UiCountry("ng", "Nigeria", "🇳🇬"),
            UiCountry("nl", "Netherlands", "🇳🇱"),
            UiCountry("no", "Norway", "🇳🇴"),
            UiCountry("nz", "New Zealand", "🇳🇿"),
            UiCountry("ph", "Philippines", "🇵🇭"),
            UiCountry("pl", "Poland", "🇵🇱"),
            UiCountry("pt", "Portugal", "🇵🇹"),
            UiCountry("ro", "Romania", "🇷🇴"),
            UiCountry("rs", "Serbia", "🇷🇸"),
            UiCountry("ru", "Russia", "🇷🇺"),
            UiCountry("sa", "Saudi Arabia", "🇸🇦"),
            UiCountry("se", "Sweden", "🇸🇪"),
            UiCountry("sg", "Singapore", "🇸🇬"),
            UiCountry("si", "Slovenia", "🇸🇮"),
            UiCountry("sk", "Slovakia", "🇸🇰"),
            UiCountry("th", "Thailand", "🇹🇭"),
            UiCountry("tr", "Turkey", "🇹🇷"),
            UiCountry("tw", "Taiwan", "🇹🇼"),
            UiCountry("ua", "Ukraine", "🇺🇦"),
            UiCountry("us", "United States", "🇺🇸"),
            UiCountry("ve", "Venezuela", "🇻🇪"),
            UiCountry("za", "South Africa", "🇿🇦")
        )
    }


    val filteredCountries = remember(
        state.searchQuery,
        countries
    ) {

        val query = state.searchQuery.trim()

        if (query.isEmpty()) {
            countries
        } else {
            countries.filter {
                it.name.contains(
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
                        text = "Select your Country"
                    )
                }
            )
        },

        bottomBar = {

            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(16.dp),

                contentAlignment =
                    Alignment.Center
            ) {

                Button(

                    onClick = {

                        navController.navigate(
                            Routes.Topic
                        )
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)

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
                .padding(16.dp),

            verticalArrangement =
                Arrangement.spacedBy(12.dp)

        ) {

            OutlinedTextField(

                value = state.searchQuery,

                onValueChange =
                    viewModel::setSearchQuery,

                modifier = Modifier
                    .fillMaxWidth(),

                leadingIcon = {

                    Icon(
                        imageVector =
                            Icons.Default.Search,
                        contentDescription =
                            "Search"
                    )
                },

                placeholder = {

                    Text(
                        text = "Search"
                    )
                },

                singleLine = true
            )

            LazyColumn(

                modifier = Modifier
                    .fillMaxSize(),

                verticalArrangement =
                    Arrangement.spacedBy(10.dp)

            ) {

                items(
                    items = filteredCountries,
                    key = { it.code }
                ) { country ->

                    val selected =
                        state.country.equals(
                            country.code,
                            ignoreCase = true
                        )

                    androidx.compose.foundation.layout.Row(

                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(
                                    10.dp
                                )
                            )
                            .background(

                                if (selected) {
                                    Color(0xFF1677FF)
                                } else {
                                    Color.Transparent
                                }
                            )
                            .clickable {

                                viewModel.setCountry(
                                    country.code
                                )
                            }
                            .padding(
                                horizontal = 12.dp,
                                vertical = 12.dp
                            ),

                        verticalAlignment =
                            Alignment.CenterVertically,

                        horizontalArrangement =
                            Arrangement.spacedBy(12.dp)

                    ) {

                        Text(

                            text =
                                country.flagEmoji,

                            modifier = Modifier
                                .size(22.dp)
                        )

                        Text(

                            text =
                                country.name,

                            style =
                                MaterialTheme
                                    .typography
                                    .bodyLarge,

                            color =
                                if (selected) {
                                    Color.White
                                } else {
                                    MaterialTheme
                                        .colorScheme
                                        .onSurface
                                }
                        )
                    }
                }

                item {

                    Spacer(
                        modifier =
                            Modifier.height(8.dp)
                    )
                }
            }
        }
    }
}