package com.example.limitlesstech.limitlessnews.presentation.authscreen.login

import com.example.limitlesstech.limitlessnews.domain.common.DomainError

data class LoginUiState(

    val username: String = "",
    val password: String = "",
    val rememberMe: Boolean = false,

    // 🔥 Field errors
    val usernameError: String? = null,
    val passwordError: String? = null,

    // 🔥 UI state
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: DomainError? = null,

    // 🔥 Form validation
    val isFormValid: Boolean = false
)