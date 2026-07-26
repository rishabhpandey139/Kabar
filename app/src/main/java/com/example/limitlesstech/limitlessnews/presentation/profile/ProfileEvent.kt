package com.example.limitlesstech.limitlessnews.presentation.profile

sealed interface ProfileEvent {

    data class UsernameChanged(
        val value: String
    ) : ProfileEvent

    data class FullNameChanged(
        val value: String
    ) : ProfileEvent

    data class EmailChanged(
        val value: String
    ) : ProfileEvent

    data class PhoneChanged(
        val value: String
    ) : ProfileEvent

    data class ImageChanged(
        val value: android.net.Uri?
    ) : ProfileEvent

    data object SaveProfile : ProfileEvent

    data object ClearErrorMessage : ProfileEvent
}