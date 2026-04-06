package com.example.limitlesstech.limitlessnews.presentation.detailScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.limitlesstech.limitlessnews.core.util.formatDateTime
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle

@Composable
fun HeaderSection(article: NewsArticle) {

    Row(verticalAlignment = Alignment.CenterVertically) {

        AsyncImage(
            model = article.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Spacer(Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(article.source)
            Text(
                text = formatDateTime(article.date),
                style = MaterialTheme.typography.bodySmall
            )
        }

        Button(onClick = {}) {
            Text("Following")
        }
    }
}