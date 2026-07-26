package com.example.limitlesstech.limitlessnews.domain.common

sealed class DomainError {
    object Network : DomainError()
    object UserAlreadyExists : DomainError()
    object InvalidCredentials : DomainError()
    object WeakPassword : DomainError()

    object EmptyEmail : DomainError()
    object EmptyPassword : DomainError()
    object InvalidEmailFormat : DomainError()
    object PasswordTooShort : DomainError()
    object PasswordMissingUpper : DomainError()
    object PasswordMissingDigit : DomainError()

    data class Unknown(val message: String?) : DomainError()

    object EmptyUsername : DomainError()

    object EmptyFullName : DomainError()

    object EmptyPhone : DomainError()

    object InvalidPhone : DomainError()

    object ProfileImageRequired : DomainError()

    object UserNotLoggedIn : DomainError()

    object ImageUploadFailed : DomainError()

    object ProfileSaveFailed : DomainError()
}