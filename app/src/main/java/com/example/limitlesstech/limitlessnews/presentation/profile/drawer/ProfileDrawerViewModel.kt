package com.example.limitlesstech.limitlessnews.presentation.profile.drawer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.usecase.auth.LogoutUseCase
import com.example.limitlesstech.limitlessnews.domain.usecase.profile.CheckProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDrawerViewModel @Inject constructor(
    private val checkProfileUseCase: CheckProfileUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        ProfileDrawerUiState()
    )

    val uiState: StateFlow<ProfileDrawerUiState> =
        _uiState.asStateFlow()

    init {
        checkProfile()
    }

    fun checkProfile() {

        viewModelScope.launch {

            _uiState.value = ProfileDrawerUiState(
                isLoading = true,
                isProfileCompleted = false,
                errorMessage = null
            )

            when (val result = checkProfileUseCase()) {

                is Result.Success -> {

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isProfileCompleted = result.data
                        )
                    }
                }

                is Result.Failure -> {

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isProfileCompleted = false,
                            errorMessage = "Unable to check profile"
                        )
                    }
                }
            }
        }
    }
    fun logout(
        onSuccess: () -> Unit
    ) {

        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            when (logoutUseCase()) {

                is Result.Success -> {

                    _uiState.update {
                        it.copy(
                            isLoading = false
                        )
                    }

                    onSuccess()
                }

                is Result.Failure -> {

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "Logout failed"
                        )
                    }
                }
            }
        }
    }
}