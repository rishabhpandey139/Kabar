
package com.example.limitlesstech.limitlessnews.presentation.common
import androidx.lifecycle.ViewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SelectionViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(SelectionUiState())
    val uiState: StateFlow<SelectionUiState> = _uiState

    fun setCountry(code: String) {
        _uiState.update { it.copy(country = code) }
    }

    fun setTopic(topic: String) {
        _uiState.update { it.copy(topic = topic) }
    }

    fun toggleSource(id: String) {
        _uiState.update { s ->
            val next = s.sources.toMutableSet()
            if (next.contains(id)) next.remove(id) else next.add(id)
            s.copy(sources = next)
        }
    }

    fun setSearchQuery(q: String) {
        _uiState.update { it.copy(searchQuery = q) }
    }

    fun clearSearch() {
        _uiState.update { it.copy(searchQuery = "") }
    }
}