package com.example.limitlesstech.limitlessnews.domain.repository

import android.net.Uri
import com.example.limitlesstech.limitlessnews.data.remote.firestoredto.FirestoreUserProfile
import com.example.limitlesstech.limitlessnews.domain.common.Result

interface ProfileRepository {

    suspend fun saveProfile(
        username: String,
        fullName: String,
        email: String,
        phone: String,
        imageUri: Uri?
    ): Result<Unit>

    suspend fun getProfile(): Result<FirestoreUserProfile>

    suspend fun updateProfile(
        username: String,
        fullName: String,
        email: String,
        phone: String,
        imageUri: Uri?
    ): Result<Unit>

    suspend fun isProfileCompleted(): Result<Boolean>
}