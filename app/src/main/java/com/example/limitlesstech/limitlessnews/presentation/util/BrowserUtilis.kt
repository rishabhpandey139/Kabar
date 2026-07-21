package com.example.limitlesstech.limitlessnews.presentation.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

object BrowserUtils {

    fun openArticle(
        context: Context,
        url: String
    ) {

        if (url.isBlank()) return

        try {

            val customTabsIntent = CustomTabsIntent.Builder()
                .setShowTitle(true)
                .build()

            customTabsIntent.launchUrl(
                context,
                Uri.parse(url)
            )

        } catch (_: ActivityNotFoundException) {

            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            )

            context.startActivity(intent)
        }
    }
}