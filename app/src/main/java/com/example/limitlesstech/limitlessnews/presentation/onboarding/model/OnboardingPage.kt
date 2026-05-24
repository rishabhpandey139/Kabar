package com.example.limitlesstech.limitlessnews.presentation.onboarding.model

import androidx.annotation.DrawableRes

data class OnboardingPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
)