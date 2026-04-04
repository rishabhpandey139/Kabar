package com.example.limitlesstech.limitlessnews.core.util

sealed class Result<out T>{
    lateinit var value: Loading

    data object Idle:Result<Nothing>()//idle - kuch hau hi nahi ab tak
    data object Loading:Result<Nothing>()//Operation chal raha hai (jaise API request ja rahi hai
    data class Success<T>(val data:T):Result<T>()//Operation successful ho gaya aur hume data mil gaya
    data class Failure(val message :String):Result<Nothing>()
}