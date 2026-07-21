package com.example.limitlesstech.limitlessnews.domain.repository

interface NetworkRepository {

    fun isInternetAvailable(): Boolean
}