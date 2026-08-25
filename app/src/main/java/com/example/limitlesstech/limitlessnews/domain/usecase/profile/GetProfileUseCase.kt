package com.example.limitlesstech.limitlessnews.domain.usecase.profile

import com.example.limitlesstech.limitlessnews.data.remote.firestoredto.FirestoreUserProfile
import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.repository.ProfileRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(): Result<FirestoreUserProfile> {
        return repository.getProfile()
    }
}