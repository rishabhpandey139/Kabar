package com.example.limitlesstech.limitlessnews.domain.repository

import android.net.Uri
import com.example.limitlesstech.limitlessnews.domain.common.Result

interface ProfileRepository {

    suspend fun saveProfile(
        username: String,
        fullName: String,
        email: String,
        phone: String,
        imageUri: Uri?
    ): Result<Unit>

}