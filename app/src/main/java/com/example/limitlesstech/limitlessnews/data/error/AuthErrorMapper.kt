package com.example.limitlesstech.limitlessnews.data.error

import com.example.limitlesstech.limitlessnews.domain.common.DomainError
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.*
import java.io.IOException

object AuthErrorMapper {

    fun mapLoginError(e: Exception): DomainError {
        return when (e) {
            is FirebaseAuthInvalidCredentialsException ->
                DomainError.InvalidCredentials

            is FirebaseNetworkException,
            is IOException ->
                DomainError.Network

            else ->
                DomainError.Unknown(e.message)
        }
    }

    fun mapSignupError(e: Exception): DomainError {
        return when (e) {
            is FirebaseAuthUserCollisionException ->
                DomainError.UserAlreadyExists

            is FirebaseAuthWeakPasswordException ->
                DomainError.WeakPassword

            is FirebaseAuthInvalidCredentialsException ->
                DomainError.InvalidCredentials

            is FirebaseNetworkException->DomainError.Network


            else ->
                DomainError.Unknown(e.message)
        }
    }

    fun mapForgotError(e: Exception): DomainError {
        return when (e) {
            is FirebaseAuthInvalidCredentialsException ->
                DomainError.InvalidCredentials

            is FirebaseNetworkException,
            is IOException ->
                DomainError.Network

            else ->
                DomainError.Unknown(e.message)
        }
    }
}