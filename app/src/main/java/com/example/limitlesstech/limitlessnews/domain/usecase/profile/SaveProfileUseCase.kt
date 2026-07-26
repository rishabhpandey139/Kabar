package com.example.limitlesstech.limitlessnews.domain.usecase.profile

import android.net.Uri
import android.util.Patterns
import com.example.limitlesstech.limitlessnews.domain.common.DomainError
import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.repository.ProfileRepository
import javax.inject.Inject

class SaveProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(
        username: String,
        fullName: String,
        email: String,
        phone: String,
        imageUri: Uri?
    ): Result<Unit> {

        if (username.isBlank()) {
            return Result.Failure(DomainError.EmptyUsername)
        }

        if (fullName.isBlank()) {
            return Result.Failure(DomainError.EmptyFullName)
        }

        if (email.isBlank()) {
            return Result.Failure(DomainError.EmptyEmail)
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Result.Failure(DomainError.InvalidEmailFormat)
        }

        if (phone.isBlank()) {
            return Result.Failure(DomainError.EmptyPhone)
        }

        val phoneRegex = Regex("^[6-9]\\d{9}$")

        if (!phoneRegex.matches(phone)) {
            return Result.Failure(DomainError.InvalidPhone)
        }

        if (imageUri == null) {
            return Result.Failure(DomainError.ProfileImageRequired)
        }

        return repository.saveProfile(
            username = username.trim(),
            fullName = fullName.trim(),
            email = email.trim(),
            phone = phone.trim(),
            imageUri = imageUri
        )
    }
}