package com.example.limitlesstech.limitlessnews.domain.usecase.profile

import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.repository.ProfileRepository
import javax.inject.Inject

class CheckProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(): Result<Boolean> {
        return repository.isProfileCompleted()
    }
}