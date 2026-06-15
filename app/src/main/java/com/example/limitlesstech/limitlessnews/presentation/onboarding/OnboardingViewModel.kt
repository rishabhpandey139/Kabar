package com.example.limitlesstech.limitlessnews.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.limitlesstech.limitlessnews.R
import com.example.limitlesstech.limitlessnews.data.local.datastore.DataStoreManager
import com.example.limitlesstech.limitlessnews.presentation.onboarding.model.OnboardingPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val dataStore: DataStoreManager
) : ViewModel() {

    private val onboardingPages = listOf(

        OnboardingPage(
            image = R.drawable.onboarding1,
            title = "Breaking News",
            description = "Stay updated with the latest headlines around the world."
        ),

        OnboardingPage(
            image = R.drawable.onboarding2,
            title = "Personalized Feed",
            description = "Choose categories and sources you love."
        ),

        OnboardingPage(
            image = R.drawable.onboarding3,
            title = "Fast & Clean",
            description = "Enjoy a modern and smooth reading experience."
        )
    )

    private val _state = MutableStateFlow(
        OnboardingUiState(
            pages = onboardingPages
        )
    )

    val state: StateFlow<OnboardingUiState> =
        _state

    // 🔥 Page changed
    fun onPageChanged(page: Int) {

        _state.update {
            it.copy(currentPage = page)
        }
    }

    // 🔥 Save onboarding completed
    fun saveOnboardingCompleted() {

        viewModelScope.launch {

            dataStore.saveOnboardingState(true)
        }
    }
}