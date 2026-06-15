package com.example.limitlesstech.limitlessnews.presentation.bookmark.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.limitlesstech.limitlessnews.domain.model.NewsArticle

@Composable
fun BookmarkList(

    modifier: Modifier = Modifier,

    articles: List<NewsArticle>,

    onArticleClick: (String) -> Unit

) {

    LazyColumn(
        modifier = modifier
    ) {

        items(

            items = articles,

            key = { it.id }

        ) { article ->

            BookmarkCard(

                article = article,

                onClick = {

                    onArticleClick(
                        article.id
                    )
                }
            )
        }
    }
}