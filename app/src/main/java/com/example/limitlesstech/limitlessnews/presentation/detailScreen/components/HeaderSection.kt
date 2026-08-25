package com.example.limitlesstech.limitlessnews.presentation.detailScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.limitlesstech.limitlessnews.core.util.formatDateTime
import com.example.limitlesstech.limitlessnews.core.util.getSourceLogoUrl
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle

@Composable
fun HeaderSection(
    article: NewsArticle
) {

    val sourceLogoUrl = getSourceLogoUrl(
        article.source
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = sourceLogoUrl,
            contentDescription = article.source,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Fit
        )

        Spacer(
            modifier = Modifier.width(8.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = article.source
            )

            Text(
                text = formatDateTime(
                    article.date
                ),
                style = MaterialTheme.typography.bodySmall
            )
        }

        Button(
            onClick = {}
        ) {

            Text(
                text = "Following"
            )
        }
    }
}