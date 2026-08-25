package com.example.limitlesstech.limitlessnews.presentation.profile

import android.net.Uri

data class ProfileUiState(

    val username: String = "",

    val fullName: String = "",

    val email: String = "",

    val phone: String = "",

    val imageUri: Uri? = null,

    val existingImageUrl: String = "",

    val isLoading: Boolean = false,

    val isSuccess: Boolean = false,

    val isProfileLoaded: Boolean = false,

    val errorMessage: String? = null
)