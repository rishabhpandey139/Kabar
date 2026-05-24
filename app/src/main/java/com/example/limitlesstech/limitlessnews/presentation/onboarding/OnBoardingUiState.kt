package com.example.limitlesstech.limitlessnews.presentation.onboarding
import com.example.limitlesstech.limitlessnews.presentation.onboarding.model.OnboardingPage


data class OnboardingUiState(//Onboarding screen ka UI data store karta hai
    val currentPage: Int = 0,//First page se start karna hai
    val pages: List<OnboardingPage> = emptyList()
)