package com.example.limitlesstech.limitlessnews.presentation.authscreen.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.limitlesstech.limitlessnews.data.local.datastore.DataStoreManager
import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val dataStore: DataStoreManager
) : ViewModel() {

    private val _state =
        MutableStateFlow(LoginUiState())

    val state: StateFlow<LoginUiState> =
        _state

    // 🔥 Email change + validation
    fun onUsernameChange(value: String) {

        _state.update { current ->

            val error = validateEmail(value)

            current.copy(
                username = value,
                usernameError = error,
                isFormValid = isFormValid(
                    value,
                    current.password
                )
            )
        }
    }

    // 🔥 Password change + validation
    fun onPasswordChange(value: String) {

        _state.update { current ->

            val error = validatePassword(value)

            current.copy(
                password = value,
                passwordError = error,
                isFormValid = isFormValid(
                    current.username,
                    value
                )
            )
        }
    }

    // 🔥 Remember me
    fun toggleRemember() {

        _state.update { current ->

            current.copy(
                rememberMe = !current.rememberMe
            )
        }
    }

    // 🔥 Login
    fun login() {

        val current = state.value

        // ❌ Stop invalid form
        if (!current.isFormValid) return

        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            val result = loginUseCase(
                current.username,
                current.password
            )

            when (result) {

                is Result.Success -> {

                    // 🔥 Save login state
                    if (current.rememberMe) {

                        dataStore.saveLoginState(true)
                    }

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
    private fun validateEmail(
        email: String
    ): String? {

        if (email.isBlank()) {
            return "Email required"
        }

        if (
            !Patterns.EMAIL_ADDRESS
                .matcher(email)
                .matches()
        ) {
            return "Invalid email"
        }

        return null
    }

    // 🔥 Password validation
    private fun validatePassword(
        password: String
    ): String? {

        if (password.isBlank()) {
            return "Password required"
        }

        if (password.length < 6) {
            return "Min 6 characters"
        }

        return null
    }

    // 🔥 Form validation
    private fun isFormValid(
        email: String,
        password: String
    ): Boolean {

        return validateEmail(email) == null &&
                validatePassword(password) == null
    }

    // 🔥 Clear snackbar error
    fun clearError() {

        _state.update {
            it.copy(error = null)
        }
    }
}