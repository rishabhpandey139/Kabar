package com.example.limitlesstech.limitlessnews.domain.repository


import com.example.limitlesstech.limitlessnews.domain.common.DomainError
import com.example.limitlesstech.limitlessnews.domain.common.Result

interface AuthRepository {

    suspend fun login(email: String, password: String): Result<Unit>

    suspend fun signUp(email: String, password: String): Result<Unit>

    suspend fun forgotPassword(email: String): Result<Unit>

}