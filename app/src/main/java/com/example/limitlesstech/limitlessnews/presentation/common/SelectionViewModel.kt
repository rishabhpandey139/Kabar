// presentation/common/SelectionViewModel.kt

package com.example.limitlesstech.limitlessnews.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.limitlesstech.limitlessnews.data.local.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectionViewModel @Inject constructor(
    private val dataStore: DataStoreManager
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(SelectionUiState())

    val uiState: StateFlow<SelectionUiState> =
        _uiState

    fun setCountry(code: String) {

        _uiState.update {
            it.copy(country = code)
        }

        viewModelScope.launch {
            dataStore.saveCountry(code)
        }
    }

    fun setTopic(topic: String) {

        _uiState.update {
            it.copy(topic = topic)
        }

        viewModelScope.launch {
            dataStore.saveTopic(topic)
        }
    }

    fun toggleSource(id: String) {

        _uiState.update { s ->

            val next = s.sources.toMutableSet()

            if (next.contains(id))
                next.remove(id)
            else
                next.add(id)

            viewModelScope.launch {
                dataStore.saveSources(next)
            }

            s.copy(sources = next)
        }
    }

    fun setSearchQuery(q: String) {

        _uiState.update {
            it.copy(searchQuery = q)
        }
    }

    fun clearSearch() {

        _uiState.update {
            it.copy(searchQuery = "")
        }
    }
}