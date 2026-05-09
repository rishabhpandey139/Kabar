package com.example.limitlesstech.limitlessnews.domain.common

sealed class Result<out T>{

    data class Success<T>(val data:T):Result<T>()//Operation successful ho gaya aur hume data mil gaya
    data class Failure(val error: DomainError):Result<Nothing>()
}