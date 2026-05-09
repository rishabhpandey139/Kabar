package com.example.limitlesstech.limitlessnews.presentation.authscreen.forgot

import com.example.limitlesstech.limitlessnews.domain.common.DomainError

data class ForgotUiState(

    val email: String = "",

    // 🔥 Field error
    val emailError: String? = null,

    // 🔥 UI state
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: DomainError? = null,

    // 🔥 Form validation
    val isFormValid: Boolean = false
)