package com.example.limitlesstech.limitlessnews.domain.usecase

import android.util.Log
import android.util.Patterns
import com.example.limitlesstech.limitlessnews.domain.common.DomainError
import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repo: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<Unit> {

        // 🔹 Email empty
        if (email.isBlank()) {
            return Result.Failure(DomainError.EmptyEmail)
        }

        // 🔹 Password empty
        if (password.isBlank()) {
            return Result.Failure(DomainError.EmptyPassword)
        }

        // 🔹 Email format
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Result.Failure(DomainError.InvalidEmailFormat)
        }

        // 🔹 Password length
        if (password.length < 6) {
            return Result.Failure(DomainError.PasswordTooShort)
        }

        // 🔹 Uppercase check
        val hasUpper = password.any { it.isUpperCase() }
        if (!hasUpper) {
            return Result.Failure(DomainError.PasswordMissingUpper)
        }

        // 🔹 Digit check
        val hasDigit = password.any { it.isDigit() }
        if (!hasDigit) {
            return Result.Failure(DomainError.PasswordMissingDigit)
        }
        Log.e("SignUpUseCase", "Validation passed, calling repository")

        // 🔥 API call
        return repo.signUp(email.trim(), password)
    }
}