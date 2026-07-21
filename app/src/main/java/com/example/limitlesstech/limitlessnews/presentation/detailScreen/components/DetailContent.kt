package com.example.limitlesstech.limitlessnews.presentation.detailScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle
import com.example.limitlesstech.limitlessnews.presentation.bookmark.BookmarkViewModel
import coil.compose.AsyncImage


@Composable
fun DetailContent(
    article: NewsArticle,
    modifier: Modifier = Modifier,
    onReadFullArticleClick: () -> Unit
){



    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {

        // 🔥 Header
        HeaderSection(article)

        Spacer(Modifier.height(16.dp))

        // 🔥 News Image
        AsyncImage(
            model = article.imageUrl,
            contentDescription = null,

            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .clip(RoundedCornerShape(20.dp)),

            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(16.dp))

        // 🔥 Category + Bookmark
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Europe",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.weight(1f))


        }

        Spacer(Modifier.height(8.dp))

        // 🔥 Title
        Text(
            text = article.title,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(12.dp))

        // 🔥 Content
        Text(
            text = article.content,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(24.dp))

        HorizontalDivider()

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Articles are truncated because of the NewsAPI Developer plan. Continue reading on the publisher's website.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(16.dp))

        if (article.link.isNotBlank()) {

            ReadFullArticleButton(
                onClick = onReadFullArticleClick
            )

            Spacer(Modifier.height(24.dp))
        }

    }
}