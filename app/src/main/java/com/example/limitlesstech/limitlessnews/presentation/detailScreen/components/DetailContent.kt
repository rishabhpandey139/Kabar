package com.example.limitlesstech.limitlessnews.presentation.detailScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle

@Composable
fun DetailContent(
    article: NewsArticle,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        HeaderSection(article)

        Spacer(Modifier.height(12.dp))

        AsyncImage(
            model = article.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(Modifier.height(12.dp))

        Text("Europe")

        Spacer(Modifier.height(8.dp))

        Text(
            text = article.title,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = article.content,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(16.dp))

    }
}