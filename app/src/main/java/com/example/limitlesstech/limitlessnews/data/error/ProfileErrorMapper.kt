package com.example.limitlesstech.limitlessnews.data.error

import com.example.limitlesstech.limitlessnews.domain.common.DomainError
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.storage.StorageException
import java.io.IOException

object ProfileErrorMapper {

    fun map(e: Exception): DomainError {

        return when (e) {

            is FirebaseNetworkException,
            is IOException ->
                DomainError.Network

            is FirebaseAuthInvalidUserException ->
                DomainError.UserNotLoggedIn

            is StorageException ->
                DomainError.ImageUploadFailed

            else ->
                DomainError.ProfileSaveFailed
        }
    }
}