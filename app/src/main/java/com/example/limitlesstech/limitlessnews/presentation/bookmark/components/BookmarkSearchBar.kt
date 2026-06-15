package com.example.limitlesstech.limitlessnews.presentation.bookmark.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BookmarkSearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {

    OutlinedTextField(

        value = query,

        onValueChange = onQueryChange,

        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            ),

        placeholder = {
            Text("Search")
        },

        leadingIcon = {

            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search"
            )
        },

        trailingIcon = {

            IconButton(
                onClick = { }
            ) {

                Icon(
                    imageVector = Icons.Outlined.Tune,
                    contentDescription = "Filter"
                )
            }
        },

        shape = RoundedCornerShape(16.dp),

        singleLine = true
    )
}