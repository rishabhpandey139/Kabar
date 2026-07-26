package com.example.limitlesstech.limitlessnews.data.remote.firestoredto

data class FirestoreUserProfile(

    val uid: String = "",

    val username: String = "",

    val fullName: String = "",

    val email: String = "",

    val phone: String = "",

    val profileImageUrl: String = "",

    val createdAt: Long = System.currentTimeMillis()
)