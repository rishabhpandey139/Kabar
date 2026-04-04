package com.example.limitlesstech.limitlessnews.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val id: String?=null,
    val name: String?=null
)