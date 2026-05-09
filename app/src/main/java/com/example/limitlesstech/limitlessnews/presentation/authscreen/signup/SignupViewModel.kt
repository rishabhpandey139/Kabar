package com.example.limitlesstech.limitlessnews.presentation.authscreen.signup

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SignupUiState())
    val state: StateFlow<SignupUiState> = _state

    // 🔥 Username change + validation
    fun onUsernameChange(value: String) {
        _state.update {
            val error = validateEmail(value)
            it.copy(
                username = value,
                usernameError = error,
                isFormValid = isFormValid(value, it.password)
            )
        }
    }

    // 🔥 Password change + validation
    fun onPasswordChange(value: String) {
        _state.update {
            val error = validatePassword(value)
            it.copy(
                password = value,
                passwordError = error,
                isFormValid = isFormValid(it.username, value)
            )
        }
    }

    fun toggleRemember() {
        _state.update { it.copy(rememberMe = !it.rememberMe) }
    }

    // 🔥 Signup
    fun signup() {
        val current = state.value

        // ❌ prevent invalid form
        if (!current.isFormValid) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            val result = signUpUseCase(
                current.username,
                current.password
            )

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
        if (email.isBlank()) return "Email required"
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return "Invalid email"
        return null
    }

    // 🔥 Password validation
    private fun validatePassword(password: String): String? {
        if (password.isBlank()) return "Password required"
        if (password.length < 6) return "Min 6 characters"
        if (!password.any { it.isUpperCase() }) return "Add uppercase letter"
        if (!password.any { it.isDigit() }) return "Add number"
        return null
    }

    // 🔥 Form validation
    private fun isFormValid(email: String, password: String): Boolean {
        return validateEmail(email) == null &&
                validatePassword(password) == null
    }

    // 🔥 Clear error (for snackbar reset)
    fun clearError() {
        _state.update { it.copy(error = null) }
    }
}