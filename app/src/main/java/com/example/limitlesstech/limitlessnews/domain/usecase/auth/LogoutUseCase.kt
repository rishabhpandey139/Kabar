package com.example.limitlesstech.limitlessnews.domain.usecase.auth

import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): Result<Unit> {
        return repository.logout()
    }
}