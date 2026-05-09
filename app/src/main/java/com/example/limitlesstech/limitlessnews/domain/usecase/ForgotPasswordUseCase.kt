package com.example.limitlesstech.limitlessnews.domain.usecase

import android.util.Patterns
import com.example.limitlesstech.limitlessnews.domain.common.DomainError
import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.repository.AuthRepository
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val repo: AuthRepository
) {

    suspend operator fun invoke(email: String): Result<Unit> {

        // 🔹 Email empty
        if (email.isBlank()) {
            return Result.Failure(DomainError.EmptyEmail)
        }

        // 🔹 Email format
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Result.Failure(DomainError.InvalidEmailFormat)
        }

        // 🔥 API call
        return repo.forgotPassword(email.trim())
    }
}