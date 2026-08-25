package com.example.limitlesstech.limitlessnews.presentation.userSelectionScreens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.limitlesstech.limitlessnews.data.local.datastore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectionViewModel @Inject constructor(
    private val dataStore: DataStoreManager
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            SelectionUiState()
        )

    val uiState: StateFlow<SelectionUiState> =
        _uiState

    init {
        observeUserSelections()
    }

    private fun observeUserSelections() {

        viewModelScope.launch {

            combine(
                dataStore.country,
                dataStore.topic,
                dataStore.sources
            ) { country, topic, sources ->

                SelectionUiState(
                    country = country,
                    topic = topic,
                    sources = sources,
                    searchQuery = _uiState.value.searchQuery
                )
            }.collect { state ->

                _uiState.value = state
            }
        }
    }

    fun setCountry(
        code: String
    ) {

        _uiState.update {
            it.copy(
                country = code
            )
        }

        viewModelScope.launch {
            dataStore.saveCountry(code)
        }
    }

    fun setTopic(
        topic: String
    ) {

        _uiState.update {
            it.copy(
                topic = topic
            )
        }

        viewModelScope.launch {
            dataStore.saveTopic(topic)
        }
    }

    fun toggleSource(
        id: String
    ) {

        val nextSources =
            _uiState.value.sources
                .toMutableSet()

        if (nextSources.contains(id)) {
            nextSources.remove(id)
        } else {
            nextSources.add(id)
        }

        _uiState.update {
            it.copy(
                sources = nextSources
            )
        }

        viewModelScope.launch {
            dataStore.saveSources(
                nextSources
            )
        }
    }

    fun setSearchQuery(
        query: String
    ) {

        _uiState.update {
            it.copy(
                searchQuery = query
            )
        }
    }

    fun clearSearch() {

        _uiState.update {
            it.copy(
                searchQuery = ""
            )
        }
    }
}