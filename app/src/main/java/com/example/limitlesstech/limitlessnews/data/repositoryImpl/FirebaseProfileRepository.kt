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
        private const val USERS_COLLECTION = "users"
    }

    override suspend fun saveProfile(
        username: String,
        fullName: String,
        email: String,
        phone: String,
        imageUri: Uri?
    ): Result<Unit> {

        return try {

            val user = firebaseAuth.currentUser
                ?: return Result.Failure(DomainError.UserNotLoggedIn)

            val imageUrl = if (imageUri != null) {
                cloudinaryUploader.uploadImage(imageUri)
            } else {
                ""
            }

            val profile = FirestoreUserProfile(
                uid = user.uid,
                username = username,
                fullName = fullName,
                email = email,
                phone = phone,
                profileImageUrl = imageUrl
            )

            firestore
                .collection(USERS_COLLECTION)
                .document(user.uid)
                .set(profile)
                .await()

            Result.Success(Unit)

        } catch (e: Exception) {

            Log.e(TAG, "Save profile error", e)

            Result.Failure(
                ProfileErrorMapper.map(e)
            )
        }
    }

    override suspend fun getProfile(): Result<FirestoreUserProfile> {

        return try {

            val user = firebaseAuth.currentUser
                ?: return Result.Failure(DomainError.UserNotLoggedIn)

            val document = firestore
                .collection(USERS_COLLECTION)
                .document(user.uid)
                .get()
                .await()

            if (!document.exists()) {
                return Result.Failure(
                    DomainError.ProfileSaveFailed
                )
            }

            val profile = document.toObject(
                FirestoreUserProfile::class.java
            ) ?: return Result.Failure(
                DomainError.ProfileSaveFailed
            )

            Result.Success(profile)

        } catch (e: Exception) {

            Log.e(TAG, "Get profile error", e)

            Result.Failure(
                ProfileErrorMapper.map(e)
            )
        }
    }

    override suspend fun updateProfile(
        username: String,
        fullName: String,
        email: String,
        phone: String,
        imageUri: Uri?
    ): Result<Unit> {

        return try {

            val user = firebaseAuth.currentUser
                ?: return Result.Failure(DomainError.UserNotLoggedIn)

            val documentRef = firestore
                .collection(USERS_COLLECTION)
                .document(user.uid)

            val oldProfile = documentRef
                .get()
                .await()
                .toObject(FirestoreUserProfile::class.java)

            val imageUrl = if (imageUri != null) {
                cloudinaryUploader.uploadImage(imageUri)
            } else {
                oldProfile?.profileImageUrl.orEmpty()
            }

            val updatedProfile = FirestoreUserProfile(
                uid = user.uid,
                username = username,
                fullName = fullName,
                email = email,
                phone = phone,
                profileImageUrl = imageUrl,
                createdAt = oldProfile?.createdAt
                    ?: System.currentTimeMillis()
            )

            documentRef
                .set(updatedProfile)
                .await()

            Result.Success(Unit)

        } catch (e: Exception) {

            Log.e(TAG, "Update profile error", e)

            Result.Failure(
                ProfileErrorMapper.map(e)
            )
        }
    }

    override suspend fun isProfileCompleted(): Result<Boolean> {

        return try {

            val user = firebaseAuth.currentUser
                ?: return Result.Success(false)

            val document = firestore
                .collection(USERS_COLLECTION)
                .document(user.uid)
                .get()
                .await()

            Result.Success(document.exists())

        } catch (e: Exception) {

            Log.e(TAG, "Check profile error", e)

            Result.Failure(
                ProfileErrorMapper.map(e)
            )
        }
    }
}