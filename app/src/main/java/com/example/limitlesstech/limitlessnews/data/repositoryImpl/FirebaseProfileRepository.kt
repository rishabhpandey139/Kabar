package com.example.limitlesstech.limitlessnews.data.repositoryImpl

import android.net.Uri
import android.util.Log
import com.example.limitlesstech.limitlessnews.core.network.CloudinaryUploader
import com.example.limitlesstech.limitlessnews.data.error.ProfileErrorMapper
import com.example.limitlesstech.limitlessnews.data.remote.firestoredto.FirestoreUserProfile
import com.example.limitlesstech.limitlessnews.domain.common.DomainError
import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.repository.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseProfileRepository @Inject constructor(

    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val cloudinaryUploader: CloudinaryUploader

) : ProfileRepository {

    companion object {
        private const val TAG = "PROFILE"
    }

    override suspend fun saveProfile(
        username: String,
        fullName: String,
        email: String,
        phone: String,
        imageUri: Uri?
    ): Result<Unit> {

        return try {

            Log.d(TAG, "saveProfile() started")

            val user = firebaseAuth.currentUser
                ?: return Result.Failure(DomainError.UserNotLoggedIn)

            val uid = user.uid

            Log.d(TAG, "User uid = $uid")

            Log.d(TAG, "Uploading image...")

            val imageUrl = if (imageUri != null) {
                cloudinaryUploader.uploadImage(imageUri)
            } else {
                ""
            }

            Log.d(TAG, "Image URL = $imageUrl")

            val profile = FirestoreUserProfile(
                uid = uid,
                username = username,
                fullName = fullName,
                email = email,
                phone = phone,
                profileImageUrl = imageUrl
            )

            Log.d(TAG, "Saving to Firestore...")

            firestore.collection("users")
                .document(uid)
                .set(profile)
                .await()

            Log.d(TAG, "Firestore save completed")

            Result.Success(Unit)

        } catch (e: Exception) {

            Log.e(TAG, "Repository Error", e)

            Result.Failure(
                ProfileErrorMapper.map(e)
            )
        }
    }
}