package com.example.limitlesstech.limitlessnews.presentation.authscreen.forgot

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.usecase.ForgotPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ForgotViewModel @Inject constructor(
    private val forgotUseCase: ForgotPasswordUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ForgotUiState())
    val state: StateFlow<ForgotUiState> = _state

    // 🔥 Realtime validation
    fun onEmailChange(value: String) {

        _state.update {

            val error = validateEmail(value)

            it.copy(
                email = value,
                emailError = error,
                isFormValid = validateEmail(value) == null
            )
        }
    }

    // 🔥 Reset password
    fun sendReset() {

        val current = state.value

        // ❌ stop invalid form
        if (!current.isFormValid) return

        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            val result = forgotUseCase(current.email)

            when (result) {

                is Result.Success -> {

                    _state.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                }

                is Result.Failure -> {

                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.error
                        )
                    }
                }
            }
        }
    }

    // 🔥 Email validation
    private fun validateEmail(email: String): String? {

        if (email.isBlank()) {
            return "Email required"
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Invalid email"
        }

        return null
    }

    // 🔥 Clear snackbar error
    fun clearError() {
        _state.update {
            it.copy(error = null)
        }
    }
}