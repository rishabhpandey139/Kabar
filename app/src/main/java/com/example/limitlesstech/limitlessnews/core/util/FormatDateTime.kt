package com.example.limitlesstech.limitlessnews.core.util

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun formatDateTime(date: String): String {
    return try {
        val zonedDateTime = ZonedDateTime.parse(date)

        val formatter = DateTimeFormatter.ofPattern("MMM d, hh:mm a")

        zonedDateTime.format(formatter)

    } catch (e: Exception) {
        date
    }
}