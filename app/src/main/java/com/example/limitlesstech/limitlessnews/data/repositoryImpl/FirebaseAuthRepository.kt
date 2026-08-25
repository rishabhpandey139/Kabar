package com.example.limitlesstech.limitlessnews.data.repositoryImpl

import android.util.Log
import com.example.limitlesstech.limitlessnews.data.error.AuthErrorMapper
import com.example.limitlesstech.limitlessnews.domain.repository.AuthRepository
import com.example.limitlesstech.limitlessnews.domain.common.DomainError
import com.example.limitlesstech.limitlessnews.domain.common.Result

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.tasks.await
import java.io.IOException
import javax.inject.Inject

class FirebaseAuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Failure(AuthErrorMapper.mapLoginError(e))
        }
    }

    override suspend fun signUp(email: String, password: String): Result<Unit> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            firebaseAuth.signOut()
            Result.Success(Unit)
        } catch (e: Exception) {
            Log.e("AuthRepo", "Signup error: ${e.message}")
            Result.Failure(AuthErrorMapper.mapSignupError(e))

        }
    }

    override suspend fun forgotPassword(email: String): Result<Unit> {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Failure(AuthErrorMapper.mapForgotError(e))
        }
    }


    override suspend fun logout(): Result<Unit> {

        return try {

            firebaseAuth.signOut()

            Result.Success(Unit)

        } catch (e: Exception) {

            Result.Failure(
                AuthErrorMapper.mapLoginError(e)
            )
        }
    }
}