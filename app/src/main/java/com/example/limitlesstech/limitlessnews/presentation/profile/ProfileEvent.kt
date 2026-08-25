package com.example.limitlesstech.limitlessnews.presentation.profile

import android.net.Uri

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
        val value: Uri?
    ) : ProfileEvent

    data object SaveProfile : ProfileEvent

    data object UpdateProfile : ProfileEvent

    data object LoadProfile : ProfileEvent

    data object ClearErrorMessage : ProfileEvent
}