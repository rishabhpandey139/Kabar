package com.example.limitlesstech.limitlessnews.presentation.common



data class SelectionUiState(
    val country: String = "in",
    val topic: String = "general",
    val sources: Set<String> = emptySet(),
    val searchQuery: String = ""
)

