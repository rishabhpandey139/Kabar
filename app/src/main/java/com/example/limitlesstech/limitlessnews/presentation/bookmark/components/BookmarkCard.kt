package com.example.limitlesstech.limitlessnews.presentation.bookmark.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.limitlesstech.limitlessnews.core.util.formatDateTime
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle

@Composable
fun BookmarkCard(
    article: NewsArticle,
    onClick: () -> Unit
) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            ),

        horizontalArrangement =
            Arrangement.SpaceBetween
    ) {

        AsyncImage(

            model = article.imageUrl,

            contentDescription = null,

            modifier = Modifier
                .size(90.dp)
                .clip(
                    RoundedCornerShape(16.dp)
                ),

            contentScale = ContentScale.Crop
        )

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(

                text = article.source,

                style = MaterialTheme
                    .typography
                    .labelMedium
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(

                text = article.title,

                maxLines = 2,

                overflow =
                    TextOverflow.Ellipsis,

                style = MaterialTheme
                    .typography
                    .titleMedium
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Row(
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(

                    text = article.source,

                    style = MaterialTheme
                        .typography
                        .bodySmall
                )

                Spacer(
                    modifier = Modifier.width(6.dp)
                )

                Text("•")

                Spacer(
                    modifier = Modifier.width(6.dp)
                )

                Text(

                    text = formatDateTime(article.date),

                    style = MaterialTheme
                        .typography
                        .bodySmall
                )
            }
        }


    }
}