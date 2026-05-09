package com.example.limitlesstech.limitlessnews.presentation.authscreen.signup

import com.example.limitlesstech.limitlessnews.domain.common.DomainError

data class SignupUiState(
    val username: String = "",
    val password: String = "",
    val rememberMe: Boolean = false,

    val usernameError: String? = null,
    val passwordError: String? = null,

    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: DomainError? = null,

    val isFormValid: Boolean = false
)